package controller;

import model.Bullet;
import java.util.ArrayList;

public class BulletController {

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
}
