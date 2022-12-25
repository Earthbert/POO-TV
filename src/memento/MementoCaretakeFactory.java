package memento;

public class MementoCaretakeFactory {
    public static <K, T> MementoCaretaker<K, T> create() {
        return new LLMementoCaretaker<>();
    }
}
