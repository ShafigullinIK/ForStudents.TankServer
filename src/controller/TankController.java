package controller;

import model.Tank;

import java.util.ArrayList;

public class TankController {

    private ArrayList<Tank> tanks = new ArrayList<>();

    public void addTank(Tank tank){
        tanks.add(tank);
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

}
