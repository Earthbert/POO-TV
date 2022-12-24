package memento;

public class MementoCareTakeFactory {
    public static <K, T> MementoCareTaker<K, T> create() {
        return new LLMementoCareTaker<>();
    }
}
