package execution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import movie.Movie;
import movie.MovieList;
import user.User;

import java.util.ArrayList;
import java.util.List;

public final class OutputWriter {
    private final ArrayNode arrayNode;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OutputWriter(final ArrayNode arrayNode) {
        this.arrayNode = arrayNode;
    }

    /**
     * Add output on arrayNode.
     * Used when an error occurs.
     */
    public void write() {
        final ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", "Error");
        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
        objectNode.putPOJO("currentUser", null);
        arrayNode.addPOJO(objectNode);
    }

    /**
     * Add output on arrayNode.
     * Used to write User and Movie List to output.
     */
    public void write(final List<Movie> movies, final User user) {
        final ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putPOJO("error", null);
        objectNode.putPOJO("currentMoviesList", MovieList.copyMovies(movies));
        objectNode.putPOJO("currentUser", new User(user));
        arrayNode.addPOJO(objectNode);
    }

    /**
     * Add output on arrayNode.
     * Used to write User to output.
     */
    public void write(final User user) {
        final ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putPOJO("error", null);
        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
        objectNode.putPOJO("currentUser", new User(user));
        arrayNode.addPOJO(objectNode);
    }
}
