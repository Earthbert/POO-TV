package memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


// MementoCareTaker List Implementation
public class LLMementoCaretaker<K, T> implements MementoCaretaker<K, T> {
    private record IdentifierAndState<K, T>(K identifier, T state) {
    }

    private final List<IdentifierAndState<K, T>> stateList = new ArrayList<>();

    /**
     * Save state of a Memento Object.
     * @param identifier object to identify state with
     * @param savedObject saved object
     */
    @Override
    public void saveState(final K identifier, final Memento<T> savedObject) {
        stateList.removeIf(x -> x.identifier.equals(identifier));
        stateList.add(new IdentifierAndState<>(identifier, savedObject.saveState()));
    }

    /**
     * Save state of a Memento Object, without identifier
     * @param savedObject saved object
     */
    @Override
    public void saveState(final Memento<T> savedObject) {
        stateList.add(new IdentifierAndState<>(null, savedObject.saveState()));
    }

    /**
     * Restore state of a Memento Object, with identifier
     * @param identifier object to identify state with
     * @param targetObject target object
     * @return if restoration was successful
     */
    @Override
    public boolean restoreState(final K identifier, final Memento<T> targetObject) {
        final Optional<IdentifierAndState<K, T>> ret = stateList.stream()
                .filter(x -> x.identifier.equals(identifier)).findFirst();
        if (ret.isPresent()) {
            targetObject.restoreState(ret.get().state);
            stateList.subList(stateList.indexOf(ret.get()), stateList.size() - 1).clear();
            return true;
        }
        return false;
    }

    /**
     * Restore last saved state of a Memento Object.
     * @param targetObject target object
     * @return if restoration was successful
     */
    @Override
    public boolean restoreLastState(final Memento<T> targetObject) {
        if (!stateList.isEmpty()) {
            targetObject.restoreState(stateList.get(stateList.size() - 1).state);
            stateList.remove(stateList.size() - 1);
            return true;
        }
        return false;
    }

    /**
     * Get state of a Memento Object, identifier
     * @param identifier object to identify state with
     * @return state
     */
    @Override
    public T getState(final Object identifier) {
        return Objects.requireNonNull(stateList.stream()
                .filter(x -> x.identifier.equals(identifier)).findFirst().orElse(null)).state;
    }

    /**
     * Get last saved state of a Memento Object
     * @return state
     */
    @Override
    public T getLastState() {
        return stateList.isEmpty() ? null : stateList.get(stateList.size() - 1).state;
    }

    /**
     * Get a list of all saved states
     * @return states
     */
    @Override
    public List<T> getAllState() {
        return stateList.stream().map(IdentifierAndState::state).toList();
    }

    /**
     * Get the number of saved states
     * @return number of saved states
     */
    @Override
    public int statesCount() {
        return stateList.size();
    }

    /**
     * Removes all previously saved states
     */
    @Override
    public void clear() {
        stateList.clear();
    }
}
