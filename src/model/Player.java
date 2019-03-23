package model;


public class Player {

    private final String name;

    private final Tank tank;

    public Player(String name, Tank tank) {
        this.name = name;
        this.tank = tank;
    }

    public String getName() {
        return name;
    }

    public Tank getTank() {
        return tank;
    }
}
