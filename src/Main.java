import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readValue(new File(args[0]), Database.class);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        Database db = Database.getInstance();
        final ArrayNode output = objectMapper.createArrayNode();
    }
}
