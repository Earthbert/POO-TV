package user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;
import movie.MovieList;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies = 15;
    private final List<Movie> purchasedMovies;
    private final List<Movie> watchedMovies;
    private final List<Movie> likedMovies;
    private final List<Movie> ratedMovies;

    @JsonCreator
    public User(@JsonProperty("credentials") final Credentials credentials) {
        this.credentials = credentials;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }

    public User(final User user) {
        this.credentials = new Credentials(user.credentials);
        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
        this.purchasedMovies = MovieList.copyMovies(user.purchasedMovies);
        this.watchedMovies = MovieList.copyMovies(user.watchedMovies);
        this.likedMovies = MovieList.copyMovies(user.likedMovies);
        this.ratedMovies = MovieList.copyMovies(user.ratedMovies);
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public List<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public List<Movie> getLikedMovies() {
        return likedMovies;
    }

    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }
}
