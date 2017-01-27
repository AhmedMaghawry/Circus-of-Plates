package circus.of.plates.model;
import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import circus.of.plates.controller.Game;


public class LevelOne implements Level,Serializable {

    private int plateSpeed = 80;
    private int plateMovement = 4;
    private int clownSpeed = 5;
    private int timeLimits = 5;
    private int timeBetweenPlates = 3000;
    private int numOfClowns = 1;
	private static Logger log = LogManager.getLogger(LevelOne.class.getName());

    @Override
    public int getSpeedOfClown() {
        return clownSpeed;
    }

    @Override
    public int getSpeedOfPlates() {
        return plateSpeed;
    }

    @Override
    public int getNumberOfClowns() {
        return numOfClowns;
    }

    @Override
    public int getTimeLimits() {
        return timeLimits;
    }

    @Override
    public int getTimeBetweenPlates() {
        return timeBetweenPlates;
    }

    @Override
    public int getPlateMovement() {
        return plateMovement;
    }

    @Override
    public Level levelUp() {
    	log.info("level up, hurray!!");
        return new LevelTwo();
    }
}
