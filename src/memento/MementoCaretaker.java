package memento;

import java.util.List;

public interface MementoCaretaker<K, T> {
    void saveState(K identifier, Memento<T> savedObject);

    void saveState(Memento<T> savedObject);

    boolean restoreState(K identifier, Memento<T> targetObject);

    boolean restoreLastState(Memento<T> targetObject);

    T getState(K identifier);

    T getLastState();

    List<T> getAllState();

    int statesCount();

    void clear();
}
