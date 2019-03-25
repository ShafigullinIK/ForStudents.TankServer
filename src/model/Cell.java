package model;

public class Cell implements Damageable {

    private FieldCellType cellType;

    private int health;

    private final int DEFAULT_CELL_HEALTH = 3;

    public Cell(FieldCellType cellType, int health) {
        this.cellType = cellType;
        this.health = health;
    }

    Cell(FieldCellType cellType) {
        this.cellType = cellType;
        health = DEFAULT_CELL_HEALTH;
    }

    public FieldCellType getCellType() {
        return cellType;
    }

    public int getHealth() {
        return health;
    }

    public void setCellType(FieldCellType cellType) {
        this.cellType = cellType;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void damage() {
        if(FieldCellType.BREAKABLE_WALL == cellType){
            health--;
            if(health == 0){
                cellType = FieldCellType.BACKGROUND;
            }
        }
    }
}
