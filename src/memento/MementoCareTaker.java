package memento;

public interface MementoCareTaker<K, T> {
    void saveState(K identifier, Memento<T> savedObject);

    void saveState(Memento<T> savedObject);

    boolean restoreState(K identifier, Memento<T> targetObject);

    boolean restoreLastState(Memento<T> targetObject);

    T getState(K identifier);

    T getLastState();

    void clear();
}
