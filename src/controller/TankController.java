package controller;

import model.Tank;

import java.util.ArrayList;

public class TankController implements TankListener {

    private ArrayList<Tank> tanks = new ArrayList<>();

    public void addTank(Tank tank){
        tanks.add(tank);
        tank.addListeners(this);
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public void removeInactiveTanks(){
        ArrayList<Tank> temp = new ArrayList<>(tanks.size());
        for (Tank tank: tanks) {
            if(tank.isTankStatus()){
                temp.add(tank);
            }
        }
        tanks = temp;
    }

    @Override
    public void tankInactive() {
        removeInactiveTanks();
    }
}
