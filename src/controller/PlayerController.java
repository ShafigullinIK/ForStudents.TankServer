package controller;

import model.Directions;
import model.Field;
import model.Player;
import model.Tank;

public class PlayerController {

    private final Player player;
    private final Tank tank;
    private final Field field;

    public PlayerController(Player player, Field field) {
        this.player = player;
        this.field = field;
        tank = player.getTank();
    }

    public void move(Directions direction){
        switch (direction){
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
}
