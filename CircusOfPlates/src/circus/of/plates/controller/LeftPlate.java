package circus.of.plates.controller;

import javax.swing.JPanel;
import circus.of.plates.model.*;
import circus.of.plates.model.Character;
import circus.of.plates.view.*;

public class LeftPlate extends Plate implements Shape{

    public LeftPlate(String color, int startx, int starty, int shielfWidth, Character clown, Game panel, int speed, int movement) {
        super(color, startx, starty, shielfWidth, clown, panel, speed, movement);
    }

    public void moveIt(Game panel) {
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
    }
    
}
