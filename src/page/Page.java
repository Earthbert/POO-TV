package page;

import movie.Movie;
import user.User;

import java.util.HashMap;
import java.util.List;

final public class Page {
    private User user;
    private String name;
    private Movie movie;
    private List<Movie> movies;

    public Page(final String name) {
        this.name = name;
    }

    private record Proprieties(List<String> pageLinks, List<String> features) {
    }

    private static final HashMap<String, Proprieties> PAGE_PROP = new HashMap<>();

    static {
        // Login page
        PAGE_PROP.put("login", new Proprieties(List.of(),List.of("login")));
        // Register page
        PAGE_PROP.put("register", new Proprieties(List.of(), List.of("register")));
        // Homepage not logged-in.
        PAGE_PROP.put("logout", new Proprieties(List.of("login", "register"), List.of()));
        // Logged-in Homepage
        PAGE_PROP.put("homepage", new Proprieties(List.of("movies", "upgrades", "logout"), List.of()));
        // Movies page
        PAGE_PROP.put("movies", new Proprieties(List.of("homepage", "see details", "logout", "movies"), List.of("search", "filter")));
        // Movie details page
        PAGE_PROP.put("see details", new Proprieties(List.of("homepage", "movies", "upgrades", "logout"),
            List.of("purchase", "watch", "like", "rate")));
        // Upgrades page
        PAGE_PROP.put("upgrades", new Proprieties(List.of("homepage", "movies", "logout"),
            List.of("buy premium account", "buy tokens")));
    }

    public boolean hasLinkTo(final String targetPage) {
        return PAGE_PROP.get(this.name).pageLinks.contains(targetPage);
    }

    public boolean hasFeature(final String feature) {
        return PAGE_PROP.get(this.name).features.contains(feature);
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final List<Movie> movies) {
        this.movies = movies;
    }
}
