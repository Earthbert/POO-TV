package user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;
import movie.MovieList;

import java.util.ArrayList;
import java.util.List;

public final class User {
    private static final int INITIAL_FREE_MOVIES = 15;
    private static final int PREMIUM_ACCOUNT_COST = 10;
    private static final int MOVIE_COST = 2;
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 5;

    private final Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies = INITIAL_FREE_MOVIES;
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

    /**
     * Make this user buy Count tokens.
     * @param count Count
     * @return Returns True if purchase was successful, false otherwise
     */
    public boolean buyTokens(final int count) {
        if (!credentials.spendBalance(count)) {
            return false;
        }
        this.tokensCount += count;
        return true;
    }

    /**
     * Make this user buy premium account.
     * @return Returns True if purchase was successful, false otherwise
     */
    public boolean buyPremiumAccount() {
        if (tokensCount < PREMIUM_ACCOUNT_COST) {
            return false;
        }
        tokensCount -= PREMIUM_ACCOUNT_COST;
        credentials.makePremium();
        return true;
    }

    /**
     * Make this user buy Movie.
     * @param movie Movie
     * @return Returns True if purchase was successful, false otherwise
     */
    public boolean buyMovie(final Movie movie) {
        if (credentials.isPremium() && numFreePremiumMovies > 0) {
            purchasedMovies.add(movie);
            numFreePremiumMovies--;
            return true;
        }
        if (tokensCount >= MOVIE_COST) {
            purchasedMovies.add(movie);
            tokensCount -= MOVIE_COST;
            return true;
        }
        return false;
    }

    /**
     * Make this user watch Movie.
     * @param movie Movie
     * @return Return true if action was successful, false otherwise.
     */
    public boolean watchMovie(final Movie movie) {
        if (!purchasedMovies.contains(movie)) {
            return false;
        }
        if (!watchedMovies.contains(movie)) {
            watchedMovies.add(movie);
            return true;
        }
        return true;
    }

    /**
     * Make this user like Movie.
     * @param movie Movie
     * @return Return true if action was successful, false otherwise.
     */
    public boolean likeMovie(final Movie movie) {
        if (watchedMovies.contains(movie)) {
            if (!likedMovies.contains(movie)) {
                movie.getLiked();
                likedMovies.add(movie);
            }
            return true;
        }
        return false;
    }

    /**
     * Make this user rate Movie.
     * @param movie Movie
     * @return Return true if action was successful, false otherwise.
     */
    public boolean rateMovie(final Movie movie, final int rate) {
        if (watchedMovies.contains(movie) && (rate >= MIN_RATING && rate <= MAX_RATING)) {
            if (!ratedMovies.contains(movie)) {
                movie.rate(rate);
                ratedMovies.add(movie);
            }
            return true;
        }
        return false;
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
