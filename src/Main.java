import model.Player;
import model.Point;
import view.JFrameView;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Vasya", new Point(5,5));
        Player player2 = new Player("Petya", new Point(7,5));
        JFrameView view = new JFrameView(player1, player2);
    }

}
