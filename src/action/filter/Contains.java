package action.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;

import java.util.List;

public class Contains {
    private final List<String> actors;
    private final List<String> genre;

    @JsonCreator
    private Contains(@JsonProperty("actors") final List<String> actors,
                    @JsonProperty("genre") final List<String> genre) {
        this.actors = actors;
        this.genre = genre;
    }

    List<Movie> apply(final List<Movie> movies) {
        List<Movie> filteredMovies = movies;
        if (actors != null) {
            filteredMovies = movies.stream()
                .filter(x -> x.getActors().containsAll(actors))
                .toList();
        }
        if (genre != null) {
            filteredMovies = filteredMovies.stream()
                .filter(x -> x.getActors().containsAll(genre))
                .toList();
        }
        return filteredMovies;
    }
}
