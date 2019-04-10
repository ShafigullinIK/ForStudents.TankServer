package model;

public enum Directions {
    LEFT, RIGHT, UP, DOWN;

    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }
}
