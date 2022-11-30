package database;

import action.Action;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;
import user.User;

import java.util.ArrayList;

public class Database {
    private static Database instance;
    private final ArrayList<User> users;
    private final ArrayList<Movie> movies;
    private final ArrayList<Action> actions;

    @JsonCreator
    private Database(@JsonProperty("users") final ArrayList<User> users,
                    @JsonProperty("movies") final ArrayList<Movie> movies,
                    @JsonProperty("actions") final ArrayList<Action> actions) {
        instance = this;
        this.users = users;
        this.movies = movies;
        this.actions = actions;
    }

    public static Database getInstance() {
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

}
