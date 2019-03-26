import model.Field;
import model.Game;
import model.Player;
import model.Point;
import view.JFrameView;

public class Main {
    private static final int DEFAULT_WINDOW_X_SIZE = 600;
    private static final int DEFAULT_WINDOW_Y_SIZE = 800;
    private static final int cellSize = 50;

    public static void main(String[] args) {
        Player player1 = new Player("Vasya", new Point(100,150));
        Player player2 = new Player("Petya", new Point(200,150));
        Field field = new Field(DEFAULT_WINDOW_X_SIZE, DEFAULT_WINDOW_Y_SIZE , cellSize);
        Game game = new Game(player1, player2, field);
        JFrameView view = new JFrameView(game);
    }

}
