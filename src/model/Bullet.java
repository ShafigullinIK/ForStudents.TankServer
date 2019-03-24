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
    public void move(Field field) {
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
                if(cells[x][y].getCellType() == FieldCellType.GRASS){
                    bulletPoint = new Point(bulletPoint.getX() - step, bulletPoint.getY());
                }
                break;
            case UP:
                x = (currentX) / sizeCell;
                y = (currentY - step) / sizeCell;
                if(cells[x][y].getCellType() == FieldCellType.GRASS){
                    bulletPoint = new Point(bulletPoint.getX(), bulletPoint.getY() - step);
                }
                break;
            case RIGHT:
                x = (currentX + step) / sizeCell;
                y = (currentY) / sizeCell;
                if(cells[x][y].getCellType() == FieldCellType.GRASS){
                    bulletPoint = new Point(bulletPoint.getX() + step, bulletPoint.getY());
                }
                break;
            case DOWN:
                x = (currentX) / sizeCell;
                y = (currentY + step) / sizeCell;
                if(cells[x][y].getCellType() == FieldCellType.GRASS){
                    bulletPoint = new Point(bulletPoint.getX(), bulletPoint.getY() + step);
                }
                break;
        }
    }


}
