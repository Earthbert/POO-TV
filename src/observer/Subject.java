package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void notifyObservers(final Object... args) {
        observers.forEach(x -> x.update(this, args));
    }

    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    public boolean containsObserver(final Observer observer) {
        return observers.contains(observer);
    }

    public void deleteObserver(final Observer observer) {
        observers.remove(observer);
    }

    public void deleteAllObservers() {
        observers.clear();
    }
}
