package circus.of.plates.model;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Character extends JLabel {

    private ImageIcon shapeBody = new ImageIcon(getClass().getResource("/imageResources/ClownBody.png"));
    private int characterSpeed;
    private Hand rightHand;
    private Hand leftHand;
    private int xcoordinates;
    private int ycoordinates;
    private int width;
    private int hight;
    private int windowhight;
    private int limit;
    private int points = 0;
    private int bottomShift = 30;
    
    public Character(int limit, int windowhight) {
        width = shapeBody.getIconWidth();
        hight = shapeBody.getIconHeight();
        this.limit = limit;
        this.windowhight = windowhight;
        xcoordinates = 0;
        ycoordinates = windowhight-hight-bottomShift;
        putItOn();
    }

    private void putItOn() {
        this.setIcon(shapeBody);
        this.setBounds(xcoordinates, 0, width, hight);
    }
    
    public Character addHandleft(Hand hand) {
            leftHand = hand;
            return this;
    }
    
    public Character addHandright(Hand hand) {
        rightHand = hand;
        return this;
}
    
    public boolean addInRightHand(Shape rightPlate, JPanel panel) {
        rightPlate.setRight();
        boolean flag = rightHand.addPlate(rightPlate, panel);
        if (flag)
            points++;
        return flag;
    }
    
    public boolean addInLeftHand(Shape leftPlate, JPanel panel) {
        leftPlate.setLeft();
        boolean flag = leftHand.addPlate(leftPlate, panel);
        if (flag)
            points++;
        return flag;
    }
    
    public int getPoints () {
        return points;
    }
    
    public int getSpeed () {
        return characterSpeed;
    }
    
    public boolean inRightHand(Shape rightPlate) {
        return rightHand.inRangeRight(rightPlate);
    }
    
    public boolean inLeftHand(Shape leftPlate) {
        return leftHand.inRangeLeft(leftPlate);
    }
    
    public int getHight() {
        return hight;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getXCoordinatesLeft() {
        return xcoordinates;
    }
    
    public int getXCoordinatesRight() {
        return xcoordinates + width;
    }
    
    public int getYCoordinatesLeft() {
        return ycoordinates - leftHand.platesHight()-10;
    }
    
    public int getYCoordinatesRight() {
        return ycoordinates - rightHand.platesHight()-10;
    }
    
    public Hand getLeftHand() {
        return leftHand;
    }
    
    public Hand getRightHand() {
        return rightHand;
    }
    
    public void setSpeed(int speed) {
        characterSpeed = speed;
    }
    
    public void move_right() {
        if (xcoordinates < limit - width)
            xcoordinates += characterSpeed;
        moveIt();
    }

    public void move_left() {
        if (xcoordinates > 0)
            xcoordinates -= characterSpeed;
        moveIt();
    }

    private void moveIt() {
        this.setBounds(xcoordinates, ycoordinates, width, hight);
    }
    
    public Character clone() {
        Character clone = new Character(limit, windowhight);
        clone.setLeftHand(leftHand);
        clone.setRightHand(rightHand);
        clone.setPoints(points);
        return clone;
    }
    
    public void setLeftHand(Hand hand) {
        leftHand = hand;
    }
    
    public void setRightHand(Hand hand) {
        rightHand = hand;
    }
    
    public void setPoints(int point) {
        points = point;
    }
}
