package model;

public class Field {
    private final int sizeX;

    private final int sizeY;

    private final Cell[][] field;

    private final int sizeCell;


    public Field(int sizeX, int sizeY, String sourceFileName, int sizeCell) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        field = new Cell[sizeX][sizeY];
        this.sizeCell = sizeCell;
        init(sourceFileName);
    }

    public int getSizeCell() {
        return sizeCell;
    }

    private void init(String sourceFileName){
        if(sourceFileName == null){
            initDefaultField();
        }
    }

    private void initDefaultField() {
        for (int i = 0; i < sizeX; i++) {
            field[i][0] = new Cell(FieldCellType.UNBREAKABLE_WALL);
            field[i][sizeY-1] = new Cell(FieldCellType.UNBREAKABLE_WALL);
        }
        for (int i = 0; i < sizeY; i++) {
            field[0][i] = new Cell(FieldCellType.UNBREAKABLE_WALL);
            field[sizeX-1][i] = new Cell(FieldCellType.UNBREAKABLE_WALL);
        }
    }


}
