package controller;

import model.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerConsoleController extends KeyAdapter {

    private final Player player;
    private final Tank tank;
    private final Field field;
    private final BulletController bulletController;
    private final TankController tankController;
    private final CommandController commandController;

    public PlayerConsoleController(Player player, Field field, BulletController bulletController, TankController tankController, CommandController commandController) {
        this.player = player;
        this.field = field;
        tank = player.getTank();
        this.bulletController = bulletController;
        this.tankController = tankController;
        this.commandController = commandController;
    }

//    private void move(Directions direction) {
//        tank.setTankDirection(direction);
//
//        ArrayList<Tank> tanks = tankController.getTanks();
//        tank.move(field, tanks);
//    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(tank.getTankHealth() <= 0){
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                commandController.addCommand(Directions.LEFT.toString());
                System.out.println(commandController);
                break;
            case KeyEvent.VK_D:
                commandController.addCommand(Directions.RIGHT.toString());
                System.out.println(commandController);
                break;
            case KeyEvent.VK_S:
                commandController.addCommand(Directions.DOWN.toString());
                System.out.println(commandController);
                break;
            case KeyEvent.VK_W:
                commandController.addCommand(Directions.UP.toString());
                System.out.println(commandController);
                break;
            case KeyEvent.VK_SPACE:
                commandController.addCommand("fire");
                System.out.println(commandController);
                break;
        }

    }
}
