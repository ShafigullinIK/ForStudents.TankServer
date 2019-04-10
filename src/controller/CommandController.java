package controller;

import java.util.PriorityQueue;
import java.util.Queue;

public class CommandController {
    private PriorityQueue<String> queue = new PriorityQueue<>();

    public CommandController() {

    }

    public void addCommand(String command){
        queue.add(command);
    }

    public String poolCommand(){
        return queue.poll();
    }

    @Override
    public String toString(){
        return queue.toString();
    }



}
