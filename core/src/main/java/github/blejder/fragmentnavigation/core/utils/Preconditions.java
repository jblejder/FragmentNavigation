package github.blejder.fragmentnavigation.core.utils;

public class Preconditions {

    public static void checkNotNull(Object reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
    }

    public static void checkNotNull(Object reference, String message) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(message));
        }
    }

    public static void checkTrue(boolean statement, String message) {
        if (!statement) {
            throw new IllegalStateException(String.valueOf(message));
        }
    }
}
