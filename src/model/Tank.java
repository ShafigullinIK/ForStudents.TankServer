package model;

public class Tank implements Moveable{

    private Point tankPoint;

    private Directions tankDirection;

    private int tankHealth;

    private int step;

    public Tank(Point currentPoint, Directions tankDirection, int health, int step) {
        this.tankPoint = currentPoint;
        this.tankDirection = tankDirection;
        this.tankHealth = health;
        this.step = step;
    }

    public Point getTankPoint() {
        return tankPoint;
    }

    public Directions getTankDirection() {
        return tankDirection;
    }

    public int getTankHealth() {
        return tankHealth;
    }

    public void setTankPoint(Point tankPoint) {
        this.tankPoint = tankPoint;
    }

    public void setTankDirection(Directions tankDirection) {
        this.tankDirection = tankDirection;
    }

    public void setTankHealth(int tankHealth) {
        this.tankHealth = tankHealth;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public void move() {
        switch (tankDirection){
            case LEFT:
                tankPoint = new Point(tankPoint.getX() - step, tankPoint.getY());
                break;
            case UP:
                tankPoint = new Point(tankPoint.getX(), tankPoint.getY() - step);
                break;
            case RIGHT:
                tankPoint = new Point(tankPoint.getX() + step, tankPoint.getY());
                break;
            case DOWN:
                tankPoint = new Point(tankPoint.getX(), tankPoint.getY() + step);
                break;
        }
    }
}
