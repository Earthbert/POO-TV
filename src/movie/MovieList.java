package movie;

import database.Database;
import user.User;

import java.util.*;

public final class MovieList {
    private MovieList() {
    }

    /**
     * Return available movies to a specific user.
     *
     * @param movies Movie list
     * @param user   User
     * @return Available Movies
     */
    public static List<Movie> available(final List<Movie> movies, final User user) {
        final ArrayList<Movie> availableMovies = new ArrayList<>();
        for (final Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                availableMovies.add(movie);
            }
        }
        return availableMovies;
    }

    /**
     * Creates deep copy of elements in a Movie list.
     * Some fields of Movie class are not deep-copied, because they don't change.
     *
     * @param movies Movie list
     * @return new list
     */
    public static List<Movie> copyMovies(final List<Movie> movies) {
        if (movies == null) {
            return null;
        }
        final ArrayList<Movie> copiedMovies = new ArrayList<>();
        for (final Movie movie : movies) {
            copiedMovies.add(new Movie(movie));
        }
        return copiedMovies;
    }

    /**
     * Get a movie from Movie list by name.
     *
     * @param movies Movie List
     * @param name   name of movie
     * @return movie
     */
    public static Optional<Movie> getMovie(final List<Movie> movies, final String name) {
        return movies.stream().filter(x -> x.getName().equals(name)).findFirst();
    }

    /**
     * Search through a Movie list.
     *
     * @param movies    Movie list
     * @param startWith String that must at the start of the Movie name
     * @return searched Movie list
     */
    public static List<Movie> searchMovie(final List<Movie> movies, final String startWith) {
        final List<Movie> matchingMovies = new ArrayList<>();
        for (final Movie movie : movies) {
            if (movie.getName().startsWith(startWith)) {
                matchingMovies.add(movie);
            }
        }
        return matchingMovies;
    }

    /**
     * Generates recommendation for a user.
     * @param user user
     * @return recommend movie, or null no recommendation was found
     */
    public static Movie generateRecommendation(final User user) {
        final TreeMap<String, Integer> genreLikes = new TreeMap<>();
        for (final Movie movie : user.getLikedMovies()) {
            movie.getGenres().forEach(x -> genreLikes.put(x, genreLikes.getOrDefault(x, 0)));
        }

        final List<String> sortedGenres = genreLikes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey).toList();

        final List<Movie> moviesSorted = available(Database.getInstance().getMovies(), user)
                .stream().sorted(Comparator.comparing(Movie::getNumLikes).reversed())
                .toList();

        for (final String genre : sortedGenres) {
            final Optional<Movie> recommendedMovie = moviesSorted.stream()
                    .filter(x -> x.getGenres().contains(genre) && !user.getWatchedMovies().contains(x))
                    .findFirst();
            if (recommendedMovie.isPresent()) {
                return recommendedMovie.get();
            }
        }
        return null;
    }
}
