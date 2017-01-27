package circus.of.plates.controller;


import java.util.ArrayList;
import java.util.Random;

import circus.of.plates.model.Plate;
import circus.of.plates.model.Character;

public class ObjectPoolPlate {
    public  ArrayList<Plate> inUseleftPlates = new ArrayList<Plate>();
    public  ArrayList<Plate> inUserightPlates = new ArrayList<Plate>();
    private  Plate plate;
    private static  ObjectPoolPlate instance = new ObjectPoolPlate();
    
    private ObjectPoolPlate(){};
    
    public static  ObjectPoolPlate getInstance(){
        return instance;
    }
    
    public  Plate getPlate(String color ,int x, int y, int shielfWidth, Character clown, Game panel, int speed, int movement,String type) {
        if(type.equals("left")){
            return getleftPlate(color ,x, y, shielfWidth, clown, panel, speed, movement);
        } else {
            return getrightPlate(color ,x, y, shielfWidth, clown, panel, speed, movement);
        }
    }
    
    private  Plate getleftPlate(String color ,int x, int y, int shielfWidth, Character clown, Game panel, int speed, int movement){
            if(inUseleftPlates.isEmpty()){
                plate = new LeftPlate(color, x, y, shielfWidth, clown, panel, speed, movement);
            } else {    
                for(int i = 0 ; i < inUseleftPlates.size(); i++){
                    if(inUseleftPlates.get(i).getColor().equals(color)){
                        plate =  inUseleftPlates.get(i);
                        break;
                    } else if (i == inUseleftPlates.size()-1){
                        plate = new LeftPlate(color, x, y, shielfWidth, clown, panel, speed, movement);
                    }
                }
            }
            return plate;
    }
    private  Plate getrightPlate(String color , int x, int y, int shielfWidth, Character clown, Game panel, int speed, int movement){
        if(inUserightPlates.isEmpty()){
            plate = new RightPlate(color, x, y, shielfWidth, clown, panel, speed, movement);
        } else {    
            for(int i = 0 ; i < inUserightPlates.size(); i++){
                if(inUserightPlates.get(i).getColor().equals(color)){
                    plate =  inUserightPlates.get(i);
                    break;
                } else if (i == inUserightPlates.size()-1){
                    plate = new RightPlate(color, x, y, shielfWidth, clown, panel, speed, movement);
                }
            }
        }
    return plate;
    }
    public Plate createleftPlate(Plate plate){
            inUseleftPlates.add(plate);
        return plate;
    }
    public Plate createrightPlate(Plate plate){
        inUserightPlates.add(plate);
        return plate;
    }
}