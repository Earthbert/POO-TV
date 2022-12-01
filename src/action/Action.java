package action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import user.Credentials;

public class Action {
    private final String type;
    private final String page;
    private final String feature;
    private final Filter filters;
    private final Credentials credentials;
    private final String movie;
    private final int count;
    private final String startsWith;
    private final String objectType;
    private double rate;

    @JsonCreator
    private Action(@JsonProperty("type") final String type,
                   @JsonProperty("page") final String page,
                   @JsonProperty("feature") final String feature,
                   @JsonProperty("filters") final Filter filters,
                   @JsonProperty("credentials") final Credentials credentials,
                   @JsonProperty("movie") final String movies,
                   @JsonProperty("count") final int count,
                   @JsonProperty("startsWith") final String startsWith,
                   @JsonProperty("objectType") final String objectType) {
        this.type = type;
        this.page = page;
        this.feature = feature;
        this.filters = filters;
        this.credentials = credentials;
        this.movie = movies;
        this.count = count;
        this.startsWith = startsWith;
        this.objectType = objectType;
    }

    public String getType() {
        return type;
    }

    public String getPage() {
        return page;
    }

    public String getFeature() {
        return feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public Filter getFilters() {
        return filters;
    }

    public String getMovie() {
        return movie;
    }

    public int getCount() {
        return count;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public String getObjectType() {
        return objectType;
    }

    public double getRate() {
        return rate;
    }
}