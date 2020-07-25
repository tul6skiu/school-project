package model;

import org.w3c.dom.ls.LSOutput;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;

public abstract class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable){
        observers.add(flyable);
    }


    public void unregister(Flyable flyable) {
        System.out.println(flyable.toString() + "       down");
        observers.remove(flyable);
    }

    protected void conditionsChanger() {
        List<Flyable> copyList = new ArrayList<>(observers);
        for (Flyable flyable : copyList) {
            flyable.updateConditions();
        }
    }

}
