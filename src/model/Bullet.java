package model;

public class Bullet implements Moveable{

    private Point bulletPoint;

    private Directions bulletDirection;

    private Tank owner;

    private int step;

    public Bullet(Point bulletPoint, Directions bulletDirection, Tank owner, int step) {
        this.bulletPoint = bulletPoint;
        this.bulletDirection = bulletDirection;
        this.owner = owner;
        this.step = step;
    }

    public Point getBulletPoint() {
        return bulletPoint;
    }

    public Directions getBulletDirection() {
        return bulletDirection;
    }

    public Tank getOwner() {
        return owner;
    }

    public int getStep() {
        return step;
    }

    @Override
    public void move() {
        switch (bulletDirection) {
            case LEFT:
                bulletPoint = new Point(bulletPoint.getX() - step, bulletPoint.getY());
                break;
            case UP:
                bulletPoint = new Point(bulletPoint.getX(), bulletPoint.getY() - step);
                break;
            case RIGHT:
                bulletPoint = new Point(bulletPoint.getX() + step, bulletPoint.getY());
                break;
            case DOWN:
                bulletPoint = new Point(bulletPoint.getX(), bulletPoint.getY() + step);
                break;
        }
    }


}
