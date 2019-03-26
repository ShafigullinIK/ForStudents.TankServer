package model;


public class Player {

    private final String name;

    private final Tank tank;

    private static final int DEFAULT_TANK_SIZE = Constants.CELL_SIZE;

    private static final int DEFAULT_TANK_HEALTH = Constants.TANK_HEALTH;

    private static final int DEFAULT_TANK_STEP = Constants.STEP;


    public Player(String name, Point startPoint, int health, int step, int tankSize ) {
        this.name = name;
        this.tank = new Tank(startPoint, Directions.UP, health, step, tankSize, name);
    }

    public Player(String name, Point startPoint){
        this.name = name;
        this.tank = new Tank(startPoint, Directions.UP, DEFAULT_TANK_HEALTH, DEFAULT_TANK_STEP, DEFAULT_TANK_SIZE, name);
    }

    public String getName() {
        return name;
    }

    public Tank getTank() {
        return tank;
    }
}
