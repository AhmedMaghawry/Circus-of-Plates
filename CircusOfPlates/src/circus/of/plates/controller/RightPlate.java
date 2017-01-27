package circus.of.plates.controller;

import circus.of.plates.model.*;
import circus.of.plates.model.Character;
import circus.of.plates.view.*;

import javax.swing.JPanel;

public class RightPlate extends Plate implements Shape{

    public RightPlate(String color, int startx, int starty, int shielfWidth, Character clown, Game panel, int speed, int movement) {
        super(color, startx, starty, shielfWidth, clown, panel, speed, movement);
    }

    @Override
    public void moveIt(Game panel) {
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
}
