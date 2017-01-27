package circus.of.plates.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import circus.of.plates.controller.*;
import circus.of.plates.view.*;

public class Plate extends JLabel implements Shape {

    private String color;
    private ImageIcon plate;
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

    public Plate(String color, int startx, int starty, int shielfWidth, Character clown, final Game panel, int speed,
            int movement) {
        this.color = color;
        this.speed = speed;
        this.movement = movement;
        plate = new ImageIcon(getClass().getResource("/imageResources/"+color+"Plate.png"));
        this.clown = clown;
        width = plate.getIconWidth();
        hight = plate.getIconHeight();
        xcoordinates = startx;
        ycoordinates = starty;
        this.shielfWidth = shielfWidth;
        setIcon(plate);
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
    public void moveIt(Game panel) {};

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
            if (ycoordinates > clown.getYCoordinatesLeft() && ycoordinates < clown.getYCoordinatesLeft()+10) {
                if (clown.inLeftHand(this)) {
                    clown.addInLeftHand(this, panel);
                }
            }
    }

    @Override
    public void rightchecker(Game panel) {
            if (ycoordinates > clown.getYCoordinatesRight() && ycoordinates < clown.getYCoordinatesRight()+10) {
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
