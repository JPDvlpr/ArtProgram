package observer;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable {
    private Set<IObserver> observers;

    public Observable() {
        observers = new HashSet<>();
    }

    //this method should be called in the child class
    //when something interesting happens
    protected void notifyObservers(Object... args) {
        for (IObserver observer : observers) {
            observer.update(this, args);
        }
    }
}