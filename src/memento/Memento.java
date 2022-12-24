package memento;

public interface Memento<T> {
    T saveState();

    void restoreState(T state);
}
