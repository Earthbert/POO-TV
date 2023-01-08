package genre;

import movie.Movie;
import observer.Subject;

import java.util.Objects;

import static user.UserUpdates.ADD_MOVIE;

public final class GenreSubscription extends Subject {
    private final String name;

    public GenreSubscription(final String name) {
        this.name = name;
    }

    /**
     * Notifies user of an added movie, of genre they subscribed to.
     * @param movie movie added
     */
    public void notifyAddedMovie(final Movie movie) {
        notifyObservers(ADD_MOVIE, movie);
    }

    @Override
    public boolean equals(final Object obj) {
        if (!obj.getClass().equals(GenreSubscription.class)) {
            return false;
        }
        return ((GenreSubscription) obj).getName().equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
