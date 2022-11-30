package action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sort {
    private final String rating;
    private final String duration;

    @JsonCreator
    private Sort(@JsonProperty("rating") final String rating,
                @JsonProperty("duration") final String duration) {
        this.rating = rating;
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public String getDuration() {
        return duration;
    }
}
