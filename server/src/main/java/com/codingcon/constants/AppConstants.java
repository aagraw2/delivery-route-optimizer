package main.java.com.codingcon.constants;

public final class AppConstants {

    private AppConstants() {
        // restricting instantiation
    }

    public static final char SOURCE = 's';
    public static final char DESTINATION = 'd';
    public static final char PATH = 'o';
    public static final char OBSTACLE = 'x';
    public static final char OUTPUT_PATH = '#';

    public static final String BASE_PATH = "/myApp";
    public static final String INIT_PATH = "/init-map";
    public static final String GET_AREAMAP_PATH = "/get-map";
    public static final String SET_SOURCE_PATH = "/set-source";
    public static final String TOGGLE_DEST_PATH = "/toggle-dest";
    public static final String RESET_PATH = "/reset-map";
    public static final String FIND_PATH= "/find-path";
}
