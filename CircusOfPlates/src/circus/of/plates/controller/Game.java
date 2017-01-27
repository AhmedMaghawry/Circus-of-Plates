package circus.of.plates.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import circus.of.plates.model.*;
import circus.of.plates.model.Character;
import circus.of.plates.view.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game extends JPanel {

    private Shape leftPlate;
    private Shape rightPlate;
    private Shielf leftShielf;
    private FlyweightPlate plate;
    private Shielf rightShielf;
    private ScoreBoard score;
    private int scoreboardHeight = 50;
    private Character clown;
    private final int windowWidth = 900;
    private final int windowHeight = 600;
    private final int windowXcoordinate = 0;
    private final int windowYcoordinate = 0;
    private final int plateYcoordinate = 60;
    private int plateSpeed;
    private int plateMovement;
    private int clownSpeed;
    private int timeLimits;
    private int timeBetweenPlates;
    private int numOfClowns = 1;
    private Timer timer;
    private boolean paused = false;
    private Level level;
    private Timer lev;
    private int secondCounter = 0;
    private PlateFactory plateMaker = PlateFactory.getInstance();
    private ObjectPoolPlate pooledPlates = ObjectPoolPlate.getInstance();
    private Constructor<?> triangle = null;
	private static Logger log = LogManager.getLogger(Game.class.getName());


    /**
     * Create the application.
     */
    public Game() {
        level = new LevelOne();
        initializeLevel();
        initialize();
        putLevelThings();
        ActionListener game = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                initializeLevel();
                putLevelThings();
                secondCounter++;
                if(secondCounter%(timeLimits*60) == 0) {
                    clearAll();
                    initialize();
                    level = level.levelUp();
                    secondCounter = 0;
                }
                if(clown.getLeftHand().platesHight() >= 200 || clown.getRightHand().platesHight() >= 200) {
                    pause();
                    gameOver();
                }
                if(clown.getPoints() == 10) {
                    pause();
                    win();
                }
            }
        };
        lev = new Timer(1000, game);
        lev.start();
        lev.setRepeats(true);
    }

    private void clearAll() {
        this.removeAll();
    }
    
    private void win() {
    	log.info("game won");
        JOptionPane.showMessageDialog(this, "Congratulatios , You won!!");
    }
    
    private void gameOver() {
    	log.info("game lost");
        JOptionPane.showMessageDialog(this, "You Lose :(");
    }
    private void putLevelThings() {
        log.info("Speed Up");
            clown.setSpeed(clownSpeed);
    }

    private void initializeLevel() {
        plateSpeed = level.getSpeedOfPlates();
        plateMovement = level.getPlateMovement();
        clownSpeed = level.getSpeedOfClown();
        timeLimits = level.getTimeLimits();
        timeBetweenPlates = level.getTimeBetweenPlates();
        numOfClowns = level.getNumberOfClowns();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        log.debug("Ineialize the Game");
        setLayout(null);
        putAllNew();
        setAllAdd();
        setAllTiming();
        setAllBounds();
        /*if (level.getNumberOfClowns() == 1) {
            Character second = new Character(windowWidth, windowHeight);
            Hand left = new Hand(second);
            Hand right = new Hand(second);
            second.addHandleft(left)
                  .addHandright(right);
            clown[1] = second;
            add(clown[1]);
        }*/
    }

    private void setAllTiming() {
        ActionListener platesAdder = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                log.info("new Plates added");
                addLeftPlate();
                addRightPlate();
            }
        };
        timer = new Timer(timeBetweenPlates, platesAdder);
        timer.start();
        log.info("Score Checker");
        ActionListener scoreSetter = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                score.setScoreOne( clown.getPoints()+ "");
            }
        };
        Timer timer2 = new Timer(50, scoreSetter);
        timer2.start();
    }

    private void setAllAdd() {
        add(score);
        add(clown);
        addLeftPlate();
        addRightPlate();
        add(leftShielf);
        add(rightShielf);
    }

    private void setAllBounds() {
        setBounds(windowXcoordinate, windowYcoordinate, windowWidth, windowHeight);
        score.setBounds(0, 0, windowWidth, scoreboardHeight);
            clown.setBounds(0, windowHeight - clown.getHight() - 40, clown.getWidth(), clown.getHight());
        leftShielf.setBounds(0, plateYcoordinate + scoreboardHeight, leftShielf.getWidth(),
                leftShielf.getHight());
        rightShielf.setBounds(windowWidth - rightShielf.getWidth(),
                plateYcoordinate + scoreboardHeight, rightShielf.getWidth(),
                rightShielf.getHight());
    }

    private void putAllNew() {
        score = new ScoreBoard();
        clown = new Character(windowWidth, windowHeight);
        Hand left = new Hand(clown);
        Hand right = new Hand(clown);
        clown.addHandleft(left)
             .addHandright(right);
        leftShielf = new Shielf();
        rightShielf = new Shielf();
        plate = new FlyweightPlate();
    }

    private void addLeftPlate() {
        log.info("Left Plate Added");
        Random rand = new Random();
        int x = rand.nextInt((triangle != null)?2:1);
        if(x == 0) {
        leftPlate = plateMaker.makePlate(colorGenerator(), windowXcoordinate, plateYcoordinate + scoreboardHeight-10,
                leftShielf.getWidth(), clown, this, plateSpeed, plateMovement, "Left");
        } else if (x == 1 && triangle != null) {
            try {
                leftPlate = (Shape) triangle.newInstance(colorGenerator(), windowXcoordinate, scoreboardHeight,
                        leftShielf.getWidth(), clown, this, plateSpeed, plateMovement,0);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        add((Component)leftPlate);
    }

    private void addRightPlate() {
        log.info("Right Plate Added");
        Random rand = new Random();
        int x = rand.nextInt((triangle != null)?2:1);
        if(x == 0) {
        rightPlate = plateMaker.makePlate(colorGenerator(),windowWidth, plateYcoordinate + scoreboardHeight-10,
                rightShielf.getWidth(), clown, this, plateSpeed, plateMovement, "Right");
        } else if (x == 1 && triangle != null) {
            try {
                rightPlate = (Shape) triangle.newInstance(colorGenerator(),windowWidth, scoreboardHeight,
                        rightShielf.getWidth(), clown, this, plateSpeed, plateMovement,1);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        add((Component)rightPlate);
    }

    public int getPanelWidth() {
        return windowWidth;
    }

    public int getPanelHight() {
        return windowHeight;
    }

    public Character getClowns() {
        return clown;
    }

    public void pause() {
        log.debug("Game Paused");
        score.pause();
        timer.stop();
        lev.stop();
        paused = true;
    }

    public void start() {
        log.debug("Game Start");
        score.start();
        timer.start();
        lev.start();
        paused = false;
    }
    
    public boolean getPaused() {
        return paused;
    }
    
    public int getNumofClowns() {
        return numOfClowns;
    }
    
    public SnapShot saveStateToMemento(){
        log.info("Take a snapShot");
        Character clown1 = clown.clone();
        return new SnapShot(level, secondCounter, clown1);
     }

     public void getStateFromMemento(SnapShot memento){
        level = memento.getLevel();
        Character clownTemp = memento.getClown();
        clown.setSpeed(clownTemp.getSpeed());
        //clown.setLeftHand(clownTemp.getLeftHand());
        //clown.setRightHand(clownTemp.getRightHand());
        clown.setPoints(clownTemp.getPoints());
        //System.out.println(clowns[0].getPoints()+"   "+clown[0].getPoints());
        secondCounter = memento.getTime();
        score.setSeconds((secondCounter - (secondCounter / 60)*60));
        score.setMin(secondCounter / 60);
        score.setTimer(secondCounter / 60 + ":" + (secondCounter - (secondCounter / 60)*60));
     }
     
     private String colorGenerator() {
         String[] colors = { "Black", "Red", "Green", "Blue", "Yellow" };
         Random rand = new Random();
         int index = rand.nextInt(5);
         return colors[index];
     }
     
     public void setNewShape(Constructor<?> Triangle) {
         //System.out.println("Doooo        " +Triangle.getName());
             triangle = Triangle;
     }
}
