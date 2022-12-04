import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import execution.Execution;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(new File(args[0]), Database.class);
        final ArrayNode arrayNode = objectMapper.createArrayNode();
        final Execution execution = new Execution(arrayNode);
        execution.start();
        final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), arrayNode);
    }
}
