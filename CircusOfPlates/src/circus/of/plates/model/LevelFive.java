package circus.of.plates.model;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LevelFive implements Level,Serializable {

    private int plateSpeed = 40;
    private int plateMovement = 6;
    private int clownSpeed = 7;
    private int timeLimits = 2;
    private int timeBetweenPlates = 1000;
    private int numOfClowns = 2;
	private static Logger log = LogManager.getLogger(LevelFive.class.getName());

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
        return new LevelOne();
    }
}
