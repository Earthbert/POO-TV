package memento;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

// MementoCareTaker List Implementation
public class LLMementoCareTaker<K, T> implements MementoCareTaker<K, T> {
    List<IdentifierAndState<K, T>> stateList = new ArrayList<>();

    private record IdentifierAndState<K, T>(K identifier, T state) {
    }

    @Override
    public void saveState(final K identifier, final Memento<T> savedObject) {
        stateList.add(new IdentifierAndState<>(identifier, savedObject.saveState()));
    }

    @Override
    public void saveState(final Memento<T> savedObject) {
        stateList.add(new IdentifierAndState<>(null, savedObject.saveState()));
    }

    @Override
    public boolean restoreState(final K identifier, final Memento<T> targetObject) {
        final Optional<IdentifierAndState<K, T>> ret = stateList.stream()
                .filter(x -> x.identifier.equals(identifier)).findFirst();
        if (ret.isPresent()) {
            targetObject.restoreState(ret.get().state);
            return true;
        }
        return false;
    }

    @Override
    public boolean restoreLastState(final Memento<T> targetObject) {
        if (!stateList.isEmpty()) {
            targetObject.restoreState(stateList.get(stateList.size() - 1).state);
            return true;
        }
        return false;
    }

    @Override
    public T getState(final Object identifier) {
        return Objects.requireNonNull(stateList.stream()
                .filter(x -> x.identifier.equals(identifier)).findFirst().orElse(null)).state;
    }

    @Override
    public T getLastState() {
        return stateList.isEmpty() ? null : stateList.get(stateList.size() - 1).state;
    }

    @Override
    public void clear() {
        stateList.clear();
    }
}
