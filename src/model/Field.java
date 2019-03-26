package model;

public class Field {
    private final int sizeX;

    private final int sizeY;

    private final Cell[][] field;

    private final int sizeCell;


    public Field(int windowX, int windowY, String sourceFileName, int sizeCell) {
        this.sizeX = windowX/sizeCell;
        this.sizeY = windowY/sizeCell;
        field = new Cell[sizeX][sizeY];
        this.sizeCell = sizeCell;
        init(sourceFileName);
    }

    public Field(int sizeX, int sizeY, int sizeCell){
        this(sizeX, sizeY, null, sizeCell);
    }

    public int getSizeCell() {
        return sizeCell;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Cell[][] getField() {
        return field;
    }

    private void init(String sourceFileName){
        if(sourceFileName == null){
            initDefaultField();
        }
    }

    private void initDefaultField() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                field[i][j] = new Cell(FieldCellType.BACKGROUND);
            }
        }
        field[10][10] = new Cell(FieldCellType.BREAKABLE_WALL);
        field[9][10] = new Cell(FieldCellType.BREAKABLE_WALL);
        field[8][10] = new Cell(FieldCellType.BREAKABLE_WALL);
        field[7][10] = new Cell(FieldCellType.BREAKABLE_WALL);
        field[6][10] = new Cell(FieldCellType.BREAKABLE_WALL);
        field[10][8] = new Cell(FieldCellType.WATER);
        field[9][8] = new Cell(FieldCellType.WATER);
        field[8][8] = new Cell(FieldCellType.WATER);
        field[7][8] = new Cell(FieldCellType.WATER);
        field[6][8] = new Cell(FieldCellType.WATER);
        field[6][4] = new Cell(FieldCellType.WATER);
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
