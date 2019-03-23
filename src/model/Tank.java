package model;

public class Tank {

    private Point currentPoint;

    private Directions currentDirection;

    private int health;

    public Tank(Point currentPoint, Directions currentDirection, int health) {
        this.currentPoint = currentPoint;
        this.currentDirection = currentDirection;
        this.health = health;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public Directions getCurrentDirection() {
        return currentDirection;
    }

    public int getHealth() {
        return health;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public void setCurrentDirection(Directions currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
