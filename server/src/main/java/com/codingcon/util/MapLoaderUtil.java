package main.java.com.codingcon.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MapLoaderUtil {
    static final String filePath = "src/main/resources/sample_map.txt";
    public static char[][] convertMapToMatrix() {
        char[][] matrix = null;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            List<String> rowSet = stream.collect(Collectors.toList());
            matrix = new char[rowSet.size()][];
            int i = 0;
            for (String row : rowSet) {
                matrix[i] = row.toCharArray();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
