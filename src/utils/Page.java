package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Page {
    private Page() {
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
        PAGE_PROP.put("movies", new Proprieties(List.of("homepage", "see details", "logout"), List.of("search", "filter")));
        // Movie details page
        PAGE_PROP.put("see details", new Proprieties(List.of("homepage", "movies", "upgrades", "logout"),
            List.of("purchase", "watch", "like", "rate")));
        // Upgrades page
        PAGE_PROP.put("upgrades", new Proprieties(List.of("homepage", "movies", "logout"),
            List.of("buy premium account", "buy tokens")));
    }

    public static boolean hasLinkTo(final String currentPage, final String targetPage) {
        return PAGE_PROP.get(currentPage).pageLinks.contains(targetPage);
    }

    public static boolean hasFeature(final String currentPage, final String feature) {
        return PAGE_PROP.get(currentPage).features.contains(feature);
    }
}
