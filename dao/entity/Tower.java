package dao.entity;

import dao.model.Flyable;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Chouaib
 * @Date 26-11-2021
 * @Project : Avaj-launcher-42
 */
public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        if(observers.size() != 0)
            System.out.println("---------- WEATHER IS CHANGING !!! ALERT !!! ALERT -----------");
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
