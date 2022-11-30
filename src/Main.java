import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final File inputFile = new File(args[0]);
        ObjectMapper objectMapper = new ObjectMapper();
        Database dataDatabase;
        try {
            dataDatabase = objectMapper.readValue(inputFile, Database.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
