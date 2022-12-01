package execution;

import action.Action;
import action.Filter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import movie.Movie;
import user.Credentials;
import user.User;
import utils.Page;

import java.util.List;

public class Execution {
    private final OutputWriter outputWriter;
    private User currentUser;
    private List<Movie> currentMovies;
    private String currentPage;

    private final ChangePageAction changePageAction = new ChangePageAction();
    private final OnPageAction onPageAction = new OnPageAction();

    public Execution(final ArrayNode arrayNode) {
        currentPage = "logout";
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
            if (Page.hasLinkTo(currentPage, "logout")) {
                currentPage = "logout";
                currentUser =  null;
                currentMovies = null;
            } else {
                outputWriter.write();
            }
        }

        private void login() {
            if (Page.hasLinkTo(currentPage, "login")) {
                currentPage = "login";
            } else {
                outputWriter.write();
            }
        }

        private void register() {
            if (Page.hasLinkTo(currentPage, "register")) {
                currentPage = "register";
            } else {
                outputWriter.write();
            }
        }

        private void homepage() {
            if (Page.hasLinkTo(currentPage, "homepage")) {
                currentPage = "homepage";
            } else {
                outputWriter.write();
            }
        }

        private void movies() {
            if (Page.hasLinkTo(currentPage, "movies")) {
                currentPage = "movies";
                // TODO output movies
            } else {
                outputWriter.write();
            }
        }

        private void seeDetails(String movieName) {
        }

        private void upgrades() {
            if (Page.hasLinkTo(currentPage, "upgrades")) {
                currentPage = "upgrades";
                currentMovies = null;
            } else {
                outputWriter.write();
            }
        }
    }

    private class OnPageAction {
        private void handle(Action action) {
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

        private void login(Credentials credentials) {
        }

        private void register(Credentials credentials) {
        }

        private void search(String startsWith) {
        }

        private void filter(Filter filters) {
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
