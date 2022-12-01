package execution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

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
}
