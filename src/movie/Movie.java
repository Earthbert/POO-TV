package movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Movie {
    private final String name;
    private final String year;
    private final int duration;
    private final ArrayList<String> genres;
    private final ArrayList<String> actors;
    private final ArrayList<String> countriesBanned;

    @JsonCreator
    private Movie(@JsonProperty("name") final String name,
                  @JsonProperty("year") final String year,
                  @JsonProperty("duration") final int duration,
                  @JsonProperty("genres") final ArrayList<String> genres,
                  @JsonProperty("actors") final ArrayList<String> actors,
                  @JsonProperty("countriesBanned") final ArrayList<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }
}
