package style.dx.java.util;

class AssertException extends Exception {
    public AssertException() {
        super();
    }

    public AssertException(String message) {
        super(message);
    }

    public AssertException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class AssertUtils {
    public static <T> void assertNotNull(T t) throws AssertException {
        if (t == null) {
            throw new AssertException("object should not be NULL!");
        }
    }

    public static void assertNotNull(Object... objects) throws AssertException {
        for (Object object : objects) {
            if (object == null) {
                throw new AssertException("all objects should not be NULL");
            }
        }
    }
}
