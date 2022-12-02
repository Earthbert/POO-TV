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

import java.util.*;

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
                currentPage.setMovie(null);
            } else {
                outputWriter.write();
            }
        }

        private void login() {
            if (currentPage.hasLinkTo("login")) {
                currentPage.setName("login");
                currentPage.setMovie(null);
            } else {
                outputWriter.write();
            }
        }

        private void register() {
            if (currentPage.hasLinkTo("register")) {
                currentPage.setName("register");
                currentPage.setMovie(null);
            } else {
                outputWriter.write();
            }
        }

        private void homepage() {
            if (currentPage.hasLinkTo("homepage")) {
                currentPage.setName("homepage");
                currentPage.setMovie(null);
            } else {
                outputWriter.write();
            }
        }

        private void movies() {
            if (currentPage.hasLinkTo("movies")) {
                currentPage.setName("movies");
                currentPage.setMovie(null);
                final List<Movie> currentMovies = MovieList.available(Database.getInstance().getMovies(), currentPage.getUser());
                outputWriter.write(currentMovies, currentPage.getUser());
            } else {
                outputWriter.write();
            }
        }

        private void seeDetails(final String movieName) {
            if (currentPage.hasLinkTo("see details")) {
                final List<Movie> availableMovies = MovieList.available(Database.getInstance().getMovies(), currentPage.getUser());
                final Optional<Movie> currentMovie = MovieList.getMovie(availableMovies, movieName);
                if (currentMovie.isPresent()) {
                    currentPage.setName("see details");
                    currentPage.setMovie(currentMovie.get());
                    outputWriter.write(List.of(currentMovie.get()), currentPage.getUser());
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
                currentPage.setMovie(null);
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
                case "purchase" -> purchase();
                case "watch" -> watch();
                case "like" -> like();
                case "rate" -> rate(action.getRate());
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

        private void register(final Credentials credentials) {
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

        private void filter(final Filter filter) {
            if (currentPage.hasFeature("filter")) {
                final List<Movie> availableMovies = MovieList.available(Database.getInstance().getMovies(), currentPage.getUser());
                final List<Movie> filteredMovies = filter.apply(availableMovies);
                outputWriter.write(filteredMovies, currentPage.getUser());
            } else {
                outputWriter.write();
            }
        }

        private void purchase() {
            if (currentPage.hasFeature("purchase")) {
                if (currentPage.getUser().buyMovie(currentPage.getMovie())) {
                    outputWriter.write(List.of(currentPage.getMovie()), currentPage.getUser());
                    return;
                }
            }
            outputWriter.write();
        }

        private void watch() {
            if (currentPage.hasFeature("watch")) {
                if (currentPage.getUser().watchMovie(currentPage.getMovie())) {
                    outputWriter.write(List.of(currentPage.getMovie()), currentPage.getUser());
                    return;
                }
            }
            outputWriter.write();
        }

        private void like() {
            if (currentPage.hasFeature("like")) {
                if (currentPage.getUser().likeMovie(currentPage.getMovie())) {
                    outputWriter.write(List.of(currentPage.getMovie()), currentPage.getUser());
                    return;
                }
            }
            outputWriter.write();
        }

        private void rate(final int rate) {
            if (currentPage.hasFeature("rate")) {
                if (currentPage.getUser().rateMovie(currentPage.getMovie(), rate)) {
                    outputWriter.write(List.of(currentPage.getMovie()), currentPage.getUser());
                    return;
                }
            }
            outputWriter.write();
        }

        private void buyPremium() {
            if (!currentPage.hasFeature("buy premium account") || !currentPage.getUser().buyPremiumAccount()) {
                outputWriter.write();
            }
        }

        private void buyTokens(final int count) {
            if (!currentPage.hasFeature("buy tokens") || !currentPage.getUser().buyTokens(count)) {
                outputWriter.write(currentPage.getUser());
            }
        }
    }
}
