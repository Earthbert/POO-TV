package user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;
import movie.MovieList;

import java.util.ArrayList;
import java.util.List;

final public class User {
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

    public boolean buyTokens(final int count) {
        if (!credentials.spendBalance(count)) {
            return false;
        }
        this.tokensCount += count;
        return true;
    }

    public boolean buyPremiumAccount() {
        if (tokensCount < 10) {
            return false;
        }
        tokensCount -= 10;
        credentials.makePremium();
        return true;
    }

    public boolean buyMovie(final Movie movie) {
        if (credentials.isPremium() && numFreePremiumMovies > 0) {
            purchasedMovies.add(movie);
            numFreePremiumMovies--;
            return true;
        }
        if (tokensCount >= 2) {
            purchasedMovies.add(movie);
            tokensCount -= 2;
            return true;
        }
        return false;
    }

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

    public boolean rateMovie(final Movie movie, final int rate) {
        if (watchedMovies.contains(movie) && (rate >= 1 && rate <= 5)) {
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
