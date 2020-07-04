package model;

public abstract class Tower {
    private Flyable flyable;


    public Tower(Flyable flyable) {
        this.flyable = flyable;
    }

    private void register(Flyable flyable){}


    private void unregister(Flyable flyable) {}

    private void conditionsChanger() {}
}
