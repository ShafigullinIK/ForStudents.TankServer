package model;

public class Tank implements Moveable, Damageable {

    private Point tankPoint;

    private Directions tankDirection;

    private int tankHealth;

    private int step;



    private int tankSize;

    private String tankName;

    public Tank(Point currentPoint, Directions tankDirection, int health, int step, int tankSize, String tankName) {
        this.tankPoint = currentPoint;
        this.tankDirection = tankDirection;
        this.tankHealth = health;
        this.step = step;
        this.tankSize = tankSize;
        this.tankName = tankName;
    }

    public String getTankName() {
        return tankName;
    }
    public int getTankSize() {
        return tankSize;
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

    public Bullet makeShot() {
        Point p = null;
        int halfTankSize = tankSize / 2;
        switch (tankDirection) {
            case LEFT:
                p = new Point(tankPoint.getX(), tankPoint.getY() + halfTankSize);
                break;
            case UP:
                p = new Point(tankPoint.getX() + halfTankSize, tankPoint.getY());
                break;
            case RIGHT:
                p = new Point(tankPoint.getX() + tankSize, tankPoint.getY() + halfTankSize);
                break;
            case DOWN:
                p = new Point(tankPoint.getX() + halfTankSize, tankPoint.getY() + tankSize);
        }
        return new Bullet(p, tankDirection, this, step);
    }

    @Override
    public void move(Field field) {
        int sizeCell = field.getSizeCell();
        int sizeX = field.getSizeX();
        int sizeY = field.getSizeY();
        Cell[][] cells = field.getField();
        int currentX = tankPoint.getX();
        int currentY = tankPoint.getY();
        switch (tankDirection) {
            case LEFT:
                int x = (currentX - step) / sizeCell;
                int y = (currentY) / sizeCell;
                if (cells[x][y].getCellType() == FieldCellType.BACKGROUND) {
                    tankPoint = new Point(tankPoint.getX() - step, tankPoint.getY());
                }
                break;
            case UP:
                x = (currentX) / sizeCell;
                y = (currentY - step) / sizeCell;
                if (cells[x][y].getCellType() == FieldCellType.BACKGROUND) {
                    tankPoint = new Point(tankPoint.getX(), tankPoint.getY() - step);
                }
                break;
            case RIGHT:
                x = (currentX + tankSize + step) / sizeCell;
                y = (currentY) / sizeCell;
                if (cells[x][y].getCellType() == FieldCellType.BACKGROUND) {
                    tankPoint = new Point(tankPoint.getX() + step, tankPoint.getY());
                }
                break;
            case DOWN:
                x = (currentX) / sizeCell;
                y = (currentY + tankSize + step) / sizeCell;
                if (cells[x][y].getCellType() == FieldCellType.BACKGROUND) {
                    tankPoint = new Point(tankPoint.getX(), tankPoint.getY() + step);
                }
                break;
        }
    }

    @Override
    public void damage() {
        tankHealth--;
    }

    @Override
    public String toString() {
        return "TankName: " + tankName + "|" +
                " Coord: x_"+tankPoint.getX() + " y_" + tankPoint.getY() + "|" +
                " Direction: " + tankDirection + "|"+
                " Health: " + tankHealth;
    }
}
