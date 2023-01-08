package memento;

public interface Memento<T> {

    /**
     * Save current save of object
     * @return copy of this object
     */
    T saveState();

    /**
     * Restore state of object
     * @param state state to be restored to
     */
    void restoreState(T state);
}
