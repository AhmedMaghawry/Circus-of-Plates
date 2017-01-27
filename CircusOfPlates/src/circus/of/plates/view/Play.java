package circus.of.plates.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import circus.of.plates.controller.*;
import circus.of.plates.model.Character;
import circus.of.plates.model.LevelFive;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Play implements KeyListener {

    private JFrame frame;
    private Game game; 
 // 0 for right arrow , 1 for left arrow , 2 for A, 3 for D
    private boolean[] moves = { false, false, false, false };
    private Character clown;
    private int width;
    private int hight;
    private boolean paused = false;
    private Button pause;
    private Play a = this;
    private Shots saves;
    private Button save;
    private Button load;
    private Button dynamic;
    private Constructor<?> Triangle;
	private static Logger log = LogManager.getLogger(LevelFive.class.getName());

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	log.info("game started, enjoy");
                    Play window = new Play();
                    window.frame.setVisible(true);
    }

    /**
     * Create the application.
     */
    public Play() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        saves = new Shots();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new Game();
        JPanel top = new JPanel(new GridLayout(1, 4));
        pause = new Button("Pause");
        pause.setFocusable(false);
        save = new Button("Save");
        save.setEnabled(false);
        save.setFocusable(false);
        load = new Button("Load");
        load.setEnabled(false);
        load.setFocusable(false);
        pause.setBackground(Color.RED);
        pause.addActionListener(new ButtonListener());
        save.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saves.add(game.saveStateToMemento());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
        load.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    game.getStateFromMemento(saves.get());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        dynamic = new Button("Load Class");
        dynamic.setEnabled(false);
        dynamic.setFocusable(false);
        dynamic.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                doAction();
            }
        });
        top.add(pause);
        top.add(save);
        top.add(load);
        top.add(dynamic);
        clown = game.getClowns();
        width = game.getPanelWidth();
        hight = game.getPanelHight();
        frame.setBounds(0, 0, width, hight+20);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(top,BorderLayout.NORTH);
        frame.getContentPane().add(game,BorderLayout.CENTER);
        frame.addKeyListener(this);
    }
    
    private void doAction() {
        JFileChooser loadfi = new JFileChooser();
        String path = "";
        if (loadfi.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = loadfi.getSelectedFile().getAbsolutePath();
            JarFile jarFile = null;
            try {
                jarFile = new JarFile(path);
            } catch (IOException e3) {
                // TODO Auto-generated catch block
                e3.printStackTrace();
            }
            Enumeration<JarEntry> ee = jarFile.entries();

            URL[] urls;
            URL xx = null;
            try {
                xx = new URL("jar:file:" + path+"!/");
            } catch (MalformedURLException e3) {
                // TODO Auto-generated catch block
                e3.printStackTrace();
            }
            URL[] urlss={xx} ;
            urls = urlss;
            System.out.println(urls.toString());
            URLClassLoader cl = URLClassLoader.newInstance(urls);

            while (ee.hasMoreElements()) {
                JarEntry je = ee.nextElement();
                System.out.println(je.getName());
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                System.out.println(je.getName());
                // -6 because of .class
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                System.out.println(className);
                Class c = null;
                try {
                    System.out.println(className);
                    System.out.println(cl);
                    c = cl.loadClass(className);
                    System.out.println(className);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    System.out.println(c.getName());
                        Triangle = c.getDeclaredConstructor(String.class, int.class, int.class, int.class, Character.class, Game.class, int.class, int.class, int.class);
                        Triangle.setAccessible(true);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("End");
                game.setNewShape(Triangle);
            }
          } else {
              JOptionPane.showMessageDialog(game, "There is no path selected");
          }
    }
    public void keyReleased(KeyEvent prs) {
        if (prs.getKeyCode() == KeyEvent.VK_D)
            moves[3] = false;
        if (prs.getKeyCode() == KeyEvent.VK_A)
            moves[2] = false;
        if (prs.getKeyCode() == KeyEvent.VK_RIGHT)
            moves[0] = false;
        if (prs.getKeyCode() == KeyEvent.VK_LEFT)
            moves[1] = false;
        moveNow();
    }

    public void keyPressed(KeyEvent prs) {
        if (prs.getKeyCode() == KeyEvent.VK_D)
            moves[3] = true;
        if (prs.getKeyCode() == KeyEvent.VK_A)
            moves[2] = true;
        if (prs.getKeyCode() == KeyEvent.VK_RIGHT)
            moves[0] = true;
        if (prs.getKeyCode() == KeyEvent.VK_LEFT)
            moves[1] = true;
        moveNow();
    }

    private void moveNow() {
        for (int i = 0; i < moves.length; i++) {
            if (moves[i]) {
                if (i == 0)
                    clown.move_right();
                else if (i == 1)
                    clown.move_left();
                if (clown != null) {
                    if (i == 2)
                    clown.move_left();
                    else if (i == 3)
                    clown.move_right();
                }
            }
        }
    }

    public void keyTyped(KeyEvent arg0) {}
    
    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt) 
        {
            if(!paused)
            {
                pause.setLabel("Start");
                pause.setBackground(Color.GREEN);
                paused = true;
                game.pause();
                save.setEnabled(true);
                load.setEnabled(true);
                dynamic.setEnabled(true);
                frame.removeKeyListener(a);
            }
            else
            {
                pause.setLabel("Pause");
                pause.setBackground(Color.RED);
                paused = false;
                game.start();
                save.setEnabled(false);
                load.setEnabled(false);
                dynamic.setEnabled(false);
                frame.addKeyListener(a);
            }
        }
    }
}