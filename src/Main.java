import model.*;
import view.JFrameView;

public class Main {
    private static final int DEFAULT_WINDOW_X_SIZE = Constants.CELL_SIZE *Constants.X_CELL_COUNT;
    private static final int DEFAULT_WINDOW_Y_SIZE = Constants.CELL_SIZE *Constants.Y_CELL_COUNT;


    public static void main(String[] args) {
        Player player1 = new Player("Vasya", new Point(Constants.CELL_SIZE,Constants.CELL_SIZE));
        Player player2 = new Player("Petya", new Point(Constants.CELL_SIZE *13,Constants.CELL_SIZE *13));
        Field field = new Field(DEFAULT_WINDOW_X_SIZE, DEFAULT_WINDOW_Y_SIZE, "field_files\\Field.txt", Constants.CELL_SIZE);
        Game game = new Game(player1, player2, field);
        JFrameView view = new JFrameView(game, Constants.CELL_SIZE);
    }

}
