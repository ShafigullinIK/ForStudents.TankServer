package model;

import controller.TankListener;

import java.util.ArrayList;

public class Tank implements Damageable {

    private Point tankPoint;

    private Directions tankDirection;

    private int tankHealth;

    private boolean tankStatus = true;

    private int step;

    private ArrayList<TankListener> listeners = new ArrayList<>();

    private int tankSize;

    private String tankName;

    private volatile int bulletsNumber = Constants.BULLETS_NUMBER;

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

    public int getBulletsNumber() {
        return bulletsNumber;
    }

    public boolean isTankStatus() {
        return tankStatus;
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

    public void addBullet(){
        if(bulletsNumber <10){
           bulletsNumber++;
        }

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
        bulletsNumber--;
        return new Bullet(p, tankDirection, this, step);
    }


    public void move(Field field, ArrayList<Tank> tanks) {
        int sizeCell = field.getSizeCell();
        int sizeX = field.getSizeX();
        int sizeY = field.getSizeY();
        Cell[][] cells = field.getField();
        int currentX = tankPoint.getX();
        int currentY = tankPoint.getY();
        Point newPoint;
        Cell secondCheckCell;
        switch (tankDirection) {
            case LEFT:
                int x = (currentX - step) / sizeCell;
                int y = (currentY) / sizeCell;
                newPoint = new Point(tankPoint.getX() - step, tankPoint.getY());
                if(currentY % sizeCell != 0 ){
                    secondCheckCell = cells[x][y+1];
                }else {
                    secondCheckCell = cells[x][y];
                }
                if (checkCellAndTank(cells[x][y], secondCheckCell, tanks, newPoint) ) {
                    tankPoint = newPoint;
                }
                break;
            case UP:
                x = (currentX) / sizeCell;
                y = (currentY - step) / sizeCell;
                newPoint =  new Point(tankPoint.getX(), tankPoint.getY() - step);
                if(currentX % sizeCell != 0 ){
                    secondCheckCell = cells[x+1][y];
                }else {
                    secondCheckCell = cells[x][y];
                }
                if (checkCellAndTank(cells[x][y], secondCheckCell, tanks, newPoint)) {
                    tankPoint = newPoint;
                }
                break;
            case RIGHT:
                x = (currentX + tankSize + step-1) / sizeCell;
                y = (currentY) / sizeCell;
                newPoint = new Point(tankPoint.getX() + step, tankPoint.getY());
                if(currentY % sizeCell != 0 ){
                    secondCheckCell = cells[x][y+1];
                }else {
                    secondCheckCell = cells[x][y];
                }
                if (checkCellAndTank(cells[x][y], secondCheckCell, tanks, newPoint)) {
                    tankPoint = newPoint;
                }
                break;
            case DOWN:
                x = (currentX) / sizeCell;
                y = (currentY + tankSize + step-1) / sizeCell;
                newPoint = new Point(tankPoint.getX(), tankPoint.getY() + step);
                if(currentX % sizeCell != 0 ){
                    secondCheckCell = cells[x+1][y];
                }else {
                    secondCheckCell = cells[x][y];
                }
                if (checkCellAndTank(cells[x][y], secondCheckCell, tanks, newPoint)) {
                    tankPoint = newPoint;
                }
                break;
        }
    }

    public void addListeners(TankListener listener){
        listeners.add(listener);
    }

    public void callListeners(){
        for (TankListener listener: listeners) {
            listener.tankInactive();
        }
    }
    @Override
    public void damage() {
        tankHealth--;
        if(tankHealth == 0) {
            tankStatus = false;
            callListeners();
        }
    }

    @Override
    public String toString() {
        return "TankName: " + tankName + "|" +
                " Coord: x_"+tankPoint.getX() + " y_" + tankPoint.getY() + "|" +
                " Direction: " + tankDirection + "|"+
                " Health: " + tankHealth + "|" +
                " BulletsNumber: " + bulletsNumber;
    }
    private boolean checkCellAndTank(Cell cell1, Cell cell2, ArrayList<Tank> tanks, Point newPoint){
        if(!checkCell(cell1)) return false;
        if(!checkCell(cell2)) return false;
        if(!checkTanks(tanks, newPoint)) return false;
        return true;
    }
    private boolean checkCell(Cell cell){
        if (cell.getCellType() == FieldCellType.BACKGROUND) {
            return true;
        }
        return false;
    }

    private boolean checkTanks(ArrayList<Tank> tanks, Point newPoint){
        for (Tank t: tanks) {
            if(!checkTank(t, newPoint)) return false;
        }
        return true;
    }

    private boolean checkTank(Tank tank, Point newPoint) {
        if(this.equals(tank)) return true;
        int tankX = tank.getTankPoint().getX();
        int tankY = tank.getTankPoint().getY();
        int thisX = newPoint.getX();
        int thisY = newPoint.getY();
        //if(tankX == thisX && tankY == thisY) return true;
        if(Math.abs(tankX - thisX) < tankSize && Math.abs(tankY - thisY) < tankSize) return false;
        return true;
    }
}
