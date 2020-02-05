package main.java.com.codingcon.Exception;

public class MapAlreadyInitializedException extends RuntimeException {
    public MapAlreadyInitializedException() {
        super("Map Already Exists");
    }
    public MapAlreadyInitializedException(String message) {
        super(message);
    }
    public MapAlreadyInitializedException(String message, Throwable cause) {
        super(message, cause);
    }
    public MapAlreadyInitializedException(Throwable cause) {
        super(cause);
    }

}
