package movie;

import database.Database;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    /**
     * Return available movies to a specific user.
     * @param movies Movie list
     * @param user User
     * @return Available Movies
     */
    public static List<Movie> available(final List<Movie> movies, final User user) {
        final ArrayList<Movie> availableMovies = new ArrayList<>();
        for (final Movie movie: movies) {
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                availableMovies.add(movie);
            }
        }
        return availableMovies;
    }

    /**
     * Creates deep copy of elements in a Movie list.
     * Some fields of Movie class are not deep-copied, because they don't change.
     * @param movies Movie list
     * @return new list
     */
    public static List<Movie> copyMovies(final List<Movie> movies) {
        final ArrayList<Movie> copiedMovies = new ArrayList<>();
        for (final Movie movie: movies) {
                copiedMovies.add(new Movie(movie));
        }
        return copiedMovies;
    }

    /**
     * Get a movie from Movie list by name
     * @param movies Movie List
     * @param name name of movie
     * @return list of size one if movie is in movies or empty list if movie is not present
     */
    public static List<Movie> getMovie(final List<Movie> movies, final String name) {
        final List<Movie> ret = new ArrayList<>();
        for (final Movie movie : movies) {
            if (movie.getName().equals(name)) {
                ret.add(movie);
                break;
            }
        }
        return ret;
    }
}
