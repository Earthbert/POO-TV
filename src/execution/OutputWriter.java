package execution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import movie.Movie;
import movie.MovieList;
import user.User;

import java.util.ArrayList;
import java.util.List;

public class OutputWriter {
    ArrayNode arrayNode;
    ObjectMapper objectMapper = new ObjectMapper();

    public OutputWriter(final ArrayNode arrayNode) {
        this.arrayNode = arrayNode;
    }

    public void write() {
        final ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("error", "Error");
        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
        objectNode.putPOJO("currentUser", null);
        arrayNode.addPOJO(objectNode);
    }

    public void write(List<Movie> movies, User user) {
        final ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putPOJO("error", null);
        objectNode.putPOJO("currentMoviesList", MovieList.copyMovies(movies));
        objectNode.putPOJO("currentUser", new User(user));
        arrayNode.addPOJO(objectNode);
    }

    public void write(User user) {
        final ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putPOJO("error", null);
        objectNode.putPOJO("currentMoviesList", new ArrayList<>());
        objectNode.putPOJO("currentUser", new User(user));
        arrayNode.addPOJO(objectNode);
    }
}
