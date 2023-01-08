package observer;

public interface Observer {
    /**
     * Called when subject is modified
     * @param subject subject
     * @param args information about update
     */
    void update(Subject subject, Object... args);
}
