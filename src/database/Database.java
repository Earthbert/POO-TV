package database;

import action.Action;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;
import user.User;
import java.util.ArrayList;
import java.util.List;

public final class Database {
    private static final ThreadLocal<Database> INSTANCE = new ThreadLocal<>();
    private final List<User> users;
    private final List<Movie> movies;
    private final List<Action> actions;

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
