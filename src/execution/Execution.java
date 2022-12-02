package execution;

import action.Action;
import action.filter.Filter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import movie.Movie;
import movie.MovieList;
import user.Credentials;
import user.User;
import user.UserAction;
import page.Page;

import java.util.List;

public class Execution {
    private final OutputWriter outputWriter;
    private final Page currentPage;

    private final ChangePageAction changePageAction = new ChangePageAction();
    private final OnPageAction onPageAction = new OnPageAction();

    public Execution(final ArrayNode arrayNode) {
        currentPage = new Page("logout");
        outputWriter = new OutputWriter(arrayNode);
    }

    public void start() {
        for (Action action : Database.getInstance().getActions()) {
            switch (action.getType()) {
                case "change page" -> changePageAction.handle(action);
                case "on page" -> onPageAction.handle(action);
                default -> System.err.println("Invalid Action");
            }
        }
    }

    private class ChangePageAction {
        private void handle(Action action) {
            switch (action.getPage()) {
                case "login" -> login();
                case "register" -> register();
                case "logout" -> logout();
                case "homepage" -> homepage();
                case "movies" -> movies();
                case "see details" -> seeDetails(action.getMovie());
                case "upgrades" -> upgrades();
                default -> System.err.println("Invalid Change Page Action");
            }
        }

        private void logout() {
            if (currentPage.hasLinkTo("logout")) {
                currentPage.setName("logout");
                currentPage.setUser(null);
            } else {
                outputWriter.write();
            }
        }

        private void login() {
            if (currentPage.hasLinkTo("login")) {
                currentPage.setName("login");
            } else {
                outputWriter.write();
            }
        }

        private void register() {
            if (currentPage.hasLinkTo("register")) {
                currentPage.setName("register");
            } else {
                outputWriter.write();
            }
        }

        private void homepage() {
            if (currentPage.hasLinkTo("homepage")) {
                currentPage.setName("homepage");
            } else {
                outputWriter.write();
            }
        }

        private void movies() {
            if (currentPage.hasLinkTo("movies")) {
                currentPage.setName("movies");
                final List<Movie> currentMovies = MovieList.available(Database.getInstance().getMovies(), currentPage.getUser());
                outputWriter.write(currentMovies, currentPage.getUser());
            } else {
                outputWriter.write();
            }
        }

        private void seeDetails(final String movieName) {
            if (currentPage.hasLinkTo("see details")) {
                final List<Movie> availableMovies = MovieList.available(Database.getInstance().getMovies(), currentPage.getUser());
                final List<Movie> currentMovie = MovieList.getMovie(availableMovies, movieName);
                if (currentMovie.size() > 0) {
                    currentPage.setName("see details");
                    outputWriter.write(currentMovie, currentPage.getUser());
                } else {
                    outputWriter.write();
                }
            } else {
                outputWriter.write();
            }
        }

        private void upgrades() {
            if (currentPage.hasLinkTo("upgrades")) {
                currentPage.setName("upgrades");
            } else {
                outputWriter.write();
            }
        }
    }

    private class OnPageAction {
        private void handle(final Action action) {
            switch (action.getFeature()) {
                case "login" -> login(action.getCredentials());
                case "register" -> register(action.getCredentials());
                case "search" -> search(action.getStartsWith());
                case "filter" -> filter(action.getFilters());
                case "purchase" -> purchase(action.getMovie());
                case "watch" -> watch(action.getMovie());
                case "like" -> like(action.getMovie());
                case "rate" -> rate(action.getMovie(), action.getRate());
                case "buy premium account" -> buyPremium();
                case "buy tokens" -> buyTokens(action.getCount());
                default -> System.err.println("Invalid On Page Action");
            }
        }

        private void login(final Credentials credentials) {
            if (currentPage.hasFeature("login")) {
                final User user = UserAction.login(credentials);
                if (user != null) {
                    currentPage.setName("homepage");
                    currentPage.setUser(user);
                    outputWriter.write(user);
                } else {
                    currentPage.setName("logout");
                    outputWriter.write();
                }
            } else {
                outputWriter.write();
            }
        }

        private void register(Credentials credentials) {
            if (currentPage.hasFeature("register")) {
                final User user = UserAction.register(credentials);
                if (user != null) {
                    currentPage.setName("homepage");
                    currentPage.setUser(user);
                    outputWriter.write(user);
                } else {
                    currentPage.setName("logout");
                    outputWriter.write();
                }
            } else {
                outputWriter.write();
            }
        }

        private void search(final String startsWith) {
            if (currentPage.hasFeature("search")) {
                final List<Movie> availableMovies = MovieList.available(Database.getInstance().getMovies(), currentPage.getUser());
                final List<Movie> foundMovies = MovieList.searchMovie(availableMovies, startsWith);
                outputWriter.write(foundMovies, currentPage.getUser());
            } else {
                outputWriter.write();
            }
        }

        private void filter(Filter filter) {
            if (currentPage.hasFeature("filter")) {
                final List<Movie> availableMovies = MovieList.available(Database.getInstance().getMovies(), currentPage.getUser());
                final List<Movie> filteredMovies = filter.apply(availableMovies);
                outputWriter.write(filteredMovies, currentPage.getUser());
            } else {
                outputWriter.write();
            }
        }

        private void purchase(String movie) {
        }

        private void watch(String movie) {
        }

        private void like(String movie) {
        }

        private void rate(String movie, double rate) {
        }

        private void buyPremium() {
        }

        private void buyTokens(int count) {
        }

    }
}
