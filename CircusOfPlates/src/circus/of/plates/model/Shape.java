package circus.of.plates.model;

import circus.of.plates.controller.Game;

public interface Shape {

    public void setColor(String color);
    public void setMovement(int move);
    public void setLeft();
    public void setRight();
    
    public int getHight();
    public int getWidth();
    public int getXcoord();
    public int getYcoord();
    
    public void pause();
    public void start();
    public void stopMove();
    public void rightchecker(Game panel);
    public void leftchecker(Game panel);
    public void moveIt(Game panel);
    public void setSpeed(int speed);
    public String getColor();
}
