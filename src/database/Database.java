package database;

import action.Action;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import genre.GenreSubscription;
import movie.Movie;
import user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static user.UserUpdates.DELETE_MOVIE;

public final class Database {
    private static final ThreadLocal<Database> INSTANCE = new ThreadLocal<>();
    private final List<User> users;
    private final List<Movie> movies;
    private final List<Action> actions;
    private final List<GenreSubscription> genreSubscriptions = new ArrayList<>();

    @JsonCreator
    private Database(@JsonProperty("users") final ArrayList<User> users,
                     @JsonProperty("movies") final ArrayList<Movie> movies,
                     @JsonProperty("actions") final ArrayList<Action> actions) {
        if (INSTANCE.get() != null) {
            System.err.println("Multiple Instances of Database");
            System.exit(-1);
        }
        INSTANCE.set(this);
        this.users = users;
        this.movies = movies;
        this.actions = actions;
    }

    public static Database getInstance() {
        return INSTANCE.get();
    }

    /**
     * Add a movie to the database
     * @param movie movie to be added
     * @return true if movie was added to the database, false otherwise
     */
    public boolean addMovie(final Movie movie) {
        if (!movies.contains(movie)) {
            genreSubscriptions.stream()
                    .filter(x -> movie.getGenres().contains(x.getName()))
                    .forEach(x -> x.notifyAddedMovie(movie));
            movies.add(movie);
            return true;
        }
        return false;
    }

    /**
     * Delete a movie from the database
     * @param movieName name of movie to be deleted
     * @return true if movie was deleted, false otherwise
     */
    public boolean deleteMovie(final String movieName) {
        final Optional<Movie> movieOptional = movies.stream()
                .filter(x -> x.getName().equals(movieName)).findFirst();
        if (movieOptional.isPresent()) {
            movies.remove(movieOptional.get());
            movieOptional.get().notifyObservers(DELETE_MOVIE);
            return true;
        }
        return false;
    }

    /**
     * Subscribe a user to a genre.
     * Adds user as an observer of GenreSubscription.
     * @param user subscriber
     * @param genreName genre Name
     * @return true if subscription was successful, false otherwise
     */
    public boolean subscribeToGenre(final User user, final String genreName) {
        final Optional<GenreSubscription> genre = genreSubscriptions.stream()
                .filter(x -> x.getName().equals(genreName)).findFirst();
        if (genre.isPresent()) {
            if (!genre.get().containsObserver(user)) {
                genre.get().addObserver(user);
                return true;
            }
            return false;
        }
        final GenreSubscription newGenre = new GenreSubscription(genreName);
        newGenre.addObserver(user);
        genreSubscriptions.add(newGenre);
        return true;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Action> getActions() {
        return actions;
    }

}
