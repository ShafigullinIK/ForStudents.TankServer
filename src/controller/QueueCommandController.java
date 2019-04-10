package controller;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QueueCommandController implements Runnable {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private final Player player;
    private final Tank tank;
    private final Field field;
    private final BulletController bulletController;
    private final TankController tankController;

    public QueueCommandController(Player player, Field field, BulletController bulletController, TankController tankController) {
        this.player = player;
        this.tank = player.getTank();
        this.field = field;
        this.bulletController = bulletController;
        this.tankController = tankController;
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
            try {
                String s = reader.readLine();
                switch (s) {
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
                    default:
                        System.out.println(s);
                }
            } catch (IOException e) {
                System.out.println("Ошибка считывания с консоли");
            }
        }
    }
}
