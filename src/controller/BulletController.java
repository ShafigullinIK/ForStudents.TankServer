package controller;

import model.Bullet;
import model.Field;
import model.Game;
import model.Tank;

import java.util.ArrayList;

public class BulletController implements Runnable {

    private final Game game;
    private final Field field;
    private final Tank tank1;
    private final Tank tank2;

    public BulletController(Game game) {
        this.game = game;
        field = game.getField();
        tank1 = game.getPlayer1().getTank();
        tank2 = game.getPlayer2().getTank();
        new Thread(this).start();
    }

    private ArrayList<Bullet> bullets = new ArrayList<>();

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public void removeInactiveBullets(){
        ArrayList<Bullet> temp = new ArrayList<>(bullets.size());
        for (Bullet bullet: bullets) {
            if(bullet.isBulletStatus()){
                temp.add(bullet);
            }
        }
        bullets = temp;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            count++;
            for (Bullet b : bullets) {
                b.move(field, tank1, tank2);
            }
            if(count%10 == 0 && bullets.size() > 0) {
                removeInactiveBullets();
            }
            try{
                Thread.sleep(30);
            } catch (InterruptedException e){

            }
        }

    }
}
