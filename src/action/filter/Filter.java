package action.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import movie.Movie;

import java.util.List;

final public class Filter {
    private final Sort sort;
    private final Contains contains;

    @JsonCreator
    private Filter(@JsonProperty("sort") final Sort sort,
                   @JsonProperty("contains") final Contains contains) {
        this.sort = sort;
        this.contains = contains;
    }

    public List<Movie> apply(List<Movie> movies) {
        if (contains != null) {
             movies = contains.apply(movies);
        }
        if (sort != null) {
            sort.apply(movies);
        }
        return movies;
    }
}
