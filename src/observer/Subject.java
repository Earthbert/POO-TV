package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Notify observers
     * @param args information about update
     */
    public void notifyObservers(final Object... args) {
        observers.forEach(x -> x.update(this, args));
    }

    /**
     * Add new observer.
     * Doesn't check if observer was already added.
     * @param observer to be added observer
     */
    public void addObserver(final Observer observer) {
        observers.add(observer);
    }

    /**
     * Check if subject contains an observer.
     * @param observer observer
     * @return true if observer is present, false otherwise
     */
    public boolean containsObserver(final Observer observer) {
        return observers.contains(observer);
    }

    /**
     * Remove observer
     * @param observer to be deleted observer
     */
    public void deleteObserver(final Observer observer) {
        observers.remove(observer);
    }

    /**
     * Remove all observers
     */
    public void deleteAllObservers() {
        observers.clear();
    }
}
