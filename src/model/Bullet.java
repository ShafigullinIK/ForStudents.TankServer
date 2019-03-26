package model;

import java.util.ArrayList;

public class Bullet {

    private Point bulletPoint;

    private boolean bulletStatus = true;

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

    public boolean isBulletStatus() {
        return bulletStatus;
    }

    private void move() {

    }

    public void move(Field field, ArrayList<Tank> tanks) {
        int sizeCell = field.getSizeCell();
        int sizeX = field.getSizeX();
        int sizeY = field.getSizeY();
        Cell[][] cells = field.getField();
        int currentX = bulletPoint.getX();
        int currentY = bulletPoint.getY();
        switch (bulletDirection) {
            case LEFT:
                int x = (currentX - step) / sizeCell;
                int y = (currentY) / sizeCell;
                if (checkCellAndTanks(cells[x][y], tanks)) {
                    bulletPoint = new Point(bulletPoint.getX() - step, bulletPoint.getY());
                } else {
                    bulletFinish(x, y, field);
                }
                break;
            case UP:
                x = (currentX) / sizeCell;
                y = (currentY - step) / sizeCell;
                if (checkCellAndTanks(cells[x][y], tanks)) {
                    bulletPoint = new Point(bulletPoint.getX(), bulletPoint.getY() - step);
                } else {
                    bulletFinish(x, y, field);
                }
                break;
            case RIGHT:
                x = (currentX + step) / sizeCell;
                y = (currentY) / sizeCell;
                if (checkCellAndTanks(cells[x][y], tanks)) {
                    bulletPoint = new Point(bulletPoint.getX() + step, bulletPoint.getY());
                } else {
                    bulletFinish(x, y, field);
                }
                break;
            case DOWN:
                x = (currentX) / sizeCell;
                y = (currentY + step) / sizeCell;
                if (checkCellAndTanks(cells[x][y], tanks)) {
                    bulletPoint = new Point(bulletPoint.getX(), bulletPoint.getY() + step);
                } else {
                    bulletFinish(x, y, field);
                }
                break;
        }


    }

    private boolean checkCellAndTanks(Cell cell, ArrayList<Tank> tanks) {
        if(!correctCellForBullet(cell)) return false;
        if(!checkTanks(tanks)) return false;
        return true;
    }

    private boolean checkTanks(ArrayList<Tank> tanks) {
        for (Tank tank: tanks) {
            if(!checkTank(tank)) return false;
        }
        return true;
    }

    private boolean checkTank(Tank tank) {
        if(owner.equals(tank)) return true;
        int bulletX = this.bulletPoint.getX();
        int bulletY = this.bulletPoint.getY();
        int tankX = tank.getTankPoint().getX();
        int tankY = tank.getTankPoint().getY();
        int tankSize = tank.getTankSize();
        if (checkTankOneCoord(bulletX, tankX, tankSize) &&
                checkTankOneCoord(bulletY, tankY, tankSize)) {
            tank.damage();
            this.bulletStatus = false;
            return false;
        }
        return true;
    }

    private boolean checkTankOneCoord(int bullet, int tank, int tankSize) {
        return bullet > tank && bullet < tank + tankSize;
    }

    @Override
    public String toString() {
        return "Coord: x_" + bulletPoint.getX() + " y_" + bulletPoint.getY() + "|" +
                " Direction: " + bulletDirection + "|" +
                " Owner: " + owner.getTankName();
    }

    private void bulletFinish(int x, int y, Field field) {
        Cell[][] cells = field.getField();
        switch (cells[x][y].getCellType()) {
            case BREAKABLE_WALL:
                cells[x][y].damage();
                bulletStatus = false;
                break;
            case UNBREAKABLE_WALL:
                bulletStatus = false;
        }
    }

    private boolean correctCellForBullet(Cell cell) {
        return cell.getCellType() == FieldCellType.BACKGROUND || cell.getCellType() == FieldCellType.WATER;
    }


}
