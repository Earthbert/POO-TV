package memento;

public final class MementoCaretakerFactory {

    private MementoCaretakerFactory() { }

    /**
     * Create an MementoCaretaker object that uses a LinkedList implementation
     * @return MementoCaretaker object
     * @param <K> Identifier Type
     * @param <T> Target Object Type
     */
    public static <K, T> MementoCaretaker<K, T> create() {
        return new LLMementoCaretaker<>();
    }
}
