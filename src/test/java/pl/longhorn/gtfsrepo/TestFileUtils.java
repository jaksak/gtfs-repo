package pl.longhorn.gtfsrepo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestFileUtils {

    public static Reader load(String fileName) {
        try {
            return new FileReader("src/test/resources/csv/" + fileName, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
