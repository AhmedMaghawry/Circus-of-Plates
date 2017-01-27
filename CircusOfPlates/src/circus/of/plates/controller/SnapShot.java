package circus.of.plates.controller;

import circus.of.plates.model.*;
import circus.of.plates.model.Character;

import java.io.Serializable;

public class SnapShot implements Serializable{

    private Level level;
    private int time;
    private Character clown;
    
    public SnapShot(Level level, int time, Character clown) {
        this.level = level;
        this.time = time;
        this.clown = clown;
    }

    public Level getLevel() {
        return level;
    }

    public int getTime() {
        return time;
    }

    public Character getClown() {
        return clown;
    }
}
