package user;

import java.util.Objects;

public final class Notification {
    private final String message;
    private final String movieName;

    public Notification(final String message, final String movieName) {
        this.message = message;
        this.movieName = movieName;
    }

    public Notification(final Notification notification) {
        this.message = notification.getMessage();
        this.movieName = notification.getMovieName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Notification that = (Notification) o;
        return message.equals(that.message) && movieName.equals(that.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, movieName);
    }

    public String getMessage() {
        return message;
    }

    public String getMovieName() {
        return movieName;
    }
}
