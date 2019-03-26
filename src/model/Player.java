package model;


public class Player {

    private final String name;

    private final Tank tank;

    private static final int DEFAULT_TANK_SIZE = 50;

    private static final int DEFAULT_TANK_HEALTH = 3;

    private static final int DEFAULT_TANK_STEP = 5;


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
