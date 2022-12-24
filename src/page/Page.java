package page;

import memento.Memento;
import movie.Movie;
import user.User;

import java.util.HashMap;
import java.util.List;

public final class Page implements Memento<Page> {
    private User user;
    private String type;
    private Movie movie;
    private List<Movie> movies;

    public Page(final String name) {
        this.type = name;
    }

    private Page(final Page copiedPage) {
        this.user = copiedPage.getUser();
        this.type = copiedPage.getType();
        this.movie = copiedPage.getMovie();
        this.movies = copiedPage.getMovies();
    }

    private record Proprieties(List<String> pageLinks, List<String> features) {
    }

    private static final HashMap<String, Proprieties> PAGE_PROP = new HashMap<>();

    static {
        // Login page
        PAGE_PROP.put("login", new Proprieties(
                List.of(),
                List.of("login")));
        // Register page
        PAGE_PROP.put("register", new Proprieties(
                List.of(),
                List.of("register")));
        // Homepage not logged-in.
        PAGE_PROP.put("logout", new Proprieties(
                List.of("login", "register"),
                List.of()));
        // Logged-in Homepage
        PAGE_PROP.put("homepage", new Proprieties(
                List.of("movies", "upgrades", "logout"),
                List.of()));
        // Movies page
        PAGE_PROP.put("movies", new Proprieties(
                List.of("homepage", "see details", "logout", "movies"),
                List.of("search", "filter")));
        // Movie details page
        PAGE_PROP.put("see details", new Proprieties(
                List.of("homepage", "movies", "upgrades", "logout"),
                List.of("purchase", "watch", "like", "rate")));
        // Upgrades page
        PAGE_PROP.put("upgrades", new Proprieties(
                List.of("homepage", "movies", "logout"),
                List.of("buy premium account", "buy tokens")));
    }

    /**
     * Check if this page has link to Target Page.
     * @param targetPage Target Page
     * @return True / False
     */
    public boolean hasLinkTo(final String targetPage) {
        return PAGE_PROP.get(this.type).pageLinks.contains(targetPage);
    }

    /**
     * Check if this page has Feature.
     * @param feature Feature
     * @return True / False
     */
    public boolean hasFeature(final String feature) {
        return PAGE_PROP.get(this.type).features.contains(feature);
    }

    @Override
    public Page saveState() {
        return new Page(this);
    }

    @Override
    public void restoreState(final Page state) {
        this.type = state.getType();
        this.user = state.getUser();
        this.movie = state.getMovie();
        this.movies = state.getMovies();
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
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
