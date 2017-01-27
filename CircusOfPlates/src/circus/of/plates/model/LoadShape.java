package circus.of.plates.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import circus.of.plates.controller.*;

public class LoadShape extends JLabel implements Shape {

    private String color;
    private ImageIcon pic;
    private int speed;
    protected int movement;
    protected int xcoordinates;
    protected boolean right = false;
    protected boolean left = false;
    protected int ycoordinates;
    protected int hight;
    protected int width;
    protected int shielfWidth;
    protected Character clown;
    protected Game panel;
    protected boolean move = true;
    private Timer timer;
    private int dirc;
    
    public LoadShape(String color, int startx, int starty, int shielfWidth, Character clown, final Game panel, int speed,
            int movement, int dirc) {
        pic = new ImageIcon(getClass().getResource("/imageResources/"+color+"Triangle.png"));
        xcoordinates = startx;
        ycoordinates = starty;
        this.dirc = dirc;
        this.speed = speed;
        this.movement = movement;
        this.color = color;
        this.clown = clown;
        width = pic.getIconWidth();
        hight = pic.getIconHeight();
        this.shielfWidth = shielfWidth;
        setIcon(pic);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (!panel.getPaused())
                    moveIt(panel);
            }
        };
        timer = new Timer(this.speed, actionListener);
        timer.start();
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getHight() {
        return hight;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void setMovement(int movement) {
        this.movement = movement;
    }

    @Override
    public int getXcoord() {
        return xcoordinates;
    }

    @Override
    public int getYcoord() {
        return ycoordinates;
    }

    @Override
    public void moveIt(Game panel) {
        if (dirc == 0) {
            if (move) {
                this.leftchecker(panel);
                this.rightchecker(panel);
                this.setBounds((xcoordinates < shielfWidth)?xcoordinates+=movement:xcoordinates,(xcoordinates >= shielfWidth)?ycoordinates+=movement:ycoordinates, width, hight);
            } else {
                if (right) {
                    //Edit here
                    this.setBounds(clown.getXCoordinatesRight()-42, ycoordinates, width, hight);
                } else if (left) {
                    // Edit here
                    this.setBounds(clown.getXCoordinatesLeft(), ycoordinates, width, hight);
                }
            }
        } else if (dirc == 1) {
            if(move) {
                this.rightchecker(panel);
                this.leftchecker(panel);
                this.setBounds((xcoordinates >= panel.getWidth()-shielfWidth-20)?xcoordinates-=movement:xcoordinates,(xcoordinates < panel.getWidth()-shielfWidth-20)?ycoordinates+=movement:ycoordinates, width, hight);
            } else {
                if (right) {
                    this.setBounds(clown.getXCoordinatesRight()-42, ycoordinates, width, hight);
                } else if (left) {
                    this.setBounds(clown.getXCoordinatesLeft(), ycoordinates, width, hight);
                }
            }
        }
    };

    @Override
    public void stopMove() {
        move = false;
    }

    @Override
    public void setLeft() {
        left = true;
    }

    @Override
    public void setRight() {
        right = true;
    }

    @Override
    public void leftchecker(Game panel) {
            if (ycoordinates+50 > clown.getYCoordinatesLeft() && ycoordinates+50 < clown.getYCoordinatesLeft() + 10) {
                if (clown.inLeftHand(this)) {
                    clown.addInLeftHand(this, panel);
                }
            }
    }

    @Override
    public void rightchecker(Game panel) {
            if (ycoordinates+50 > clown.getYCoordinatesRight() && ycoordinates+50 < clown.getYCoordinatesRight() + 10) {
                if (clown.inRightHand(this)) {
                    clown.addInRightHand(this, panel);
                }
            }
    }

    @Override
    public void pause() {
        timer.stop();
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }
}
