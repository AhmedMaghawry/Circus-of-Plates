package circus.of.plates.model;


public interface Level {

    public int getSpeedOfClown();
    public int getSpeedOfPlates();
    public int getNumberOfClowns();
    public int getTimeLimits();
    public int getTimeBetweenPlates();
    public int getPlateMovement();
    public Level levelUp();
}
