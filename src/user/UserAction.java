package user;

import database.Database;

import javax.xml.crypto.Data;
import java.util.Optional;

public class UserAction {
    public static User login(Credentials credentials) {
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

    public static User register(Credentials credentials) {
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