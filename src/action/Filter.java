package action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Filter {
    private final Sort sort;
    private final Contains contains;

    @JsonCreator
    private Filter(@JsonProperty("sort") final Sort sort,
                   @JsonProperty("contains") final Contains contains) {
        this.sort = sort;
        this.contains = contains;
    }

    public Sort getSort() {
        return sort;
    }

    public Contains getContains() {
        return contains;
    }
}
