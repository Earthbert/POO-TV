package memento;

import java.util.List;

public interface MementoCaretaker<K, T> {

    /**
     * Save state of a Memento Object.
     * @param identifier object to identify state with
     * @param savedObject saved object
     */
    void saveState(K identifier, Memento<T> savedObject);

    /**
     * Save state of a Memento Object, without identifier
     * @param savedObject saved object
     */
    void saveState(Memento<T> savedObject);

    /**
     * Restore state of a Memento Object, with identifier
     * @param identifier object to identify state with
     * @param targetObject target object
     * @return if restoration was successful
     */
    boolean restoreState(K identifier, Memento<T> targetObject);

    /**
     * Restore last saved state of a Memento Object.
     * @param targetObject target object
     * @return if restoration was successful
     */
    boolean restoreLastState(Memento<T> targetObject);

    /**
     * Get state of a Memento Object, identifier
     * @param identifier object to identify state with
     * @return state
     */
    T getState(K identifier);

    /**
     * Get last saved state of a Memento Object
     * @return state
     */
    T getLastState();

    /**
     * Get a list of all saved states
     * @return states
     */
    List<T> getAllState();

    /**
     * Get the number of saved states
     * @return number of saved states
     */
    int statesCount();

    /**
     * Removes all previously saved states
     */
    void clear();
}
