package pl.longhorn.gtfsrepo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestFileUtils {

    public static Reader csvReader(String fileName) {
        try {
            return new FileReader("src/test/resources/csv/" + fileName, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream zipInputStream(String fileName) {
        try {
            return new FileInputStream("src/test/resources/zip/" + fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
