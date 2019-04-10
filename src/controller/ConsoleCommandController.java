package controller;

import model.*;

import java.util.ArrayList;

public class ConsoleCommandController implements Runnable {

    private final Player player;
    private final Tank tank;
    private final Field field;
    private final BulletController bulletController;
    private final TankController tankController;
    private final CommandController commandController;

    public ConsoleCommandController(Player player, Field field, BulletController bulletController, TankController tankController, CommandController commandController) {
        this.player = player;
        this.field = field;
        tank = player.getTank();
        this.bulletController = bulletController;
        this.tankController = tankController;
        this.commandController = commandController;
        new Thread(this).start();
    }

    private void move(Directions direction) {
        tank.setTankDirection(direction);

        ArrayList<Tank> tanks = tankController.getTanks();
        tank.move(field, tanks);
    }


    @Override
    public void run() {
        while (true) {
            String command = commandController.poolCommand();
            if (command != null) {
                switch (command) {
                    case "left":
                        move(Directions.LEFT);
                        break;
                    case "up":
                        move(Directions.UP);
                        break;
                    case "right":
                        move(Directions.RIGHT);
                        break;
                    case "down":
                        move(Directions.DOWN);
                        break;
                    case "fire":
                        Bullet b = tank.makeShot();
                        bulletController.addBullet(b);
                        break;
                    default : break;
                }
            }
        }
    }
}
