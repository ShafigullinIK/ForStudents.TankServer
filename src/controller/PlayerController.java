package controller;

import model.Directions;
import model.Field;
import model.Player;
import model.Tank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerController extends KeyAdapter {

    private final Player player;
    private final Tank tank;
    private final Field field;

    public PlayerController(Player player, Field field) {
        this.player = player;
        this.field = field;
        tank = player.getTank();
    }

    public void move(Directions direction) {
        switch (direction) {
            case UP:
                tank.setTankDirection(Directions.UP);
                break;
            case RIGHT:
                tank.setTankDirection(Directions.RIGHT);
                break;
            case DOWN:
                tank.setTankDirection(Directions.DOWN);
                break;
            case LEFT:
                tank.setTankDirection(Directions.LEFT);
                break;
        }
        tank.move(field);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                move(Directions.LEFT);
                break;
            case KeyEvent.VK_D:
                move(Directions.RIGHT);
                break;
            case KeyEvent.VK_S:
                move(Directions.DOWN);
                break;
            case KeyEvent.VK_W:
                move(Directions.UP);
                break;
        }
        System.out.println(tank);
    }
}
