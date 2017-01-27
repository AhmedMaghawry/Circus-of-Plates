package circus.of.plates.controller;

import circus.of.plates.model.*;
import circus.of.plates.model.Character;
import circus.of.plates.view.*;

public class PlateFactory {
    private static PlateFactory singleton;

    private PlateFactory(){

    }
    public Plate makePlate(final String color,
            final int x, final int y, final int shielfWidth, final Character clown,
            final Game panel, final int speed, final int movement,final String type){

        if(type.equals("Left")){
            return new LeftPlate(color, x, y , shielfWidth, clown,
                    panel, speed, movement);
        }else {
            return new RightPlate(color, x, y , shielfWidth, clown,
                    panel, speed, movement);
        }

    }

    public static PlateFactory getInstance(){
        if(singleton == null){
            singleton = new PlateFactory();
        }
        return singleton;
    }




}
