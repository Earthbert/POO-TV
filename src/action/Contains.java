package action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Contains {
    private final ArrayList<String> actors;
    private final ArrayList<String> genre;

    @JsonCreator
    private Contains(@JsonProperty("actors") final ArrayList<String> actors,
                    @JsonProperty("genre") final ArrayList<String> genre) {
        this.actors = actors;
        this.genre = genre;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }
}
