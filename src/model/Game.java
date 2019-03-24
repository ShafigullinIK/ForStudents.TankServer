package model;

import java.util.ArrayList;

public class Game {

    private Player player1;

    private Player player2;

    private Field field;

    private ArrayList<Bullet> bullets = new ArrayList<>();

    public Game(Player player1, Player player2, Field field) {
        this.player1 = player1;
        this.player2 = player2;
        this.field = field;
    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public void removeInactiveBullets(){
        ArrayList<Bullet> temp = new ArrayList<>(bullets.size());
        for (Bullet bullet: bullets) {
            if(bullet.getBulletPoint().getX() > 0 && bullet.getBulletPoint().getY() > 0){
                temp.add(bullet);
            }
        }
        bullets = temp;
    }
}
