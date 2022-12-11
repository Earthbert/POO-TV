package user;

import database.Database;

import java.util.Optional;

public final class UserAction {
    private UserAction() { }

    /**
     * Log-in user from Database.
     * @param credentials Credentials of User
     * @return User or null if user doesn't exist.
     */
    public static User login(final Credentials credentials) {
        final Optional<User> optionalUser = Database.getInstance().getUsers().stream()
            .filter(x -> x.getCredentials().getName().equals(credentials.getName())).findFirst();

        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            if (user.getCredentials().getPassword().equals(credentials.getPassword())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Register a new user into Database.
     * @param credentials Credentials of User
     * @return New User of null if user is already present in Database.
     */
    public static User register(final Credentials credentials) {
        final Optional<User> optionalUser = Database.getInstance().getUsers().stream()
            .filter(x -> x.getCredentials().getName().equals(credentials.getName())).findFirst();
        if (optionalUser.isPresent()) {
            return null;

        }
        final User newUser = new User(credentials);
        Database.getInstance().getUsers().add(newUser);
        return newUser;
    }
}
