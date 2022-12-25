package genre;

import movie.Movie;
import observer.Subject;

import static user.UserUpdates.ADD_MOVIE;

public class GenreSubscription extends Subject {
    private final String name;

    public GenreSubscription(final String name) {
        this.name = name;
    }

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

    public String getName() {
        return name;
    }
}
