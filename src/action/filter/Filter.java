package action.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;

import java.util.List;

public final class Filter {
    private final Sort sort;
    private final Contains contains;

    @JsonCreator
    private Filter(@JsonProperty("sort") final Sort sort,
                   @JsonProperty("contains") final Contains contains) {
        this.sort = sort;
        this.contains = contains;
    }

    /**
     * Applies this filter over a Movie list
     * @param movies Movie list to be filtered
     * @return filtered Movie list
     */
    public List<Movie> apply(final List<Movie> movies) {
        List<Movie> filteredMovies = movies;
        if (contains != null) {
             filteredMovies = contains.apply(filteredMovies);
        }
        if (sort != null) {
            sort.apply(filteredMovies);
        }
        return filteredMovies;
    }
}
