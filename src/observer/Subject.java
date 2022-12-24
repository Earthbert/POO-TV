package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> observers = new ArrayList<>();

    void notifyObservers(final Object... args) {
        observers.forEach(x -> x.update(this, args));
    }

    void addObserver(final Observer observer) {
        observers.add(observer);
    }

    void deleteObserver(final Observer observer) {
        observers.remove(observer);
    }

    void deleteAllObservers() {
        observers.clear();
    }
}
