package main.java.com.codingcon.Exception;

public class MapObjectPositionException extends RuntimeException {
    public MapObjectPositionException() {
        super("Invalid Position of Map Object");
    }
    public MapObjectPositionException(String message) {
        super(message);
    }
    public MapObjectPositionException(String message, Throwable cause) {
        super(message, cause);
    }
    public MapObjectPositionException(Throwable cause) {
        super(cause);
    }

}
