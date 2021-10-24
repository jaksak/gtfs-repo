package pl.longhorn.gtfsrepo.zip;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZipEntryCallerTest {

    @Test
    public void shouldExploreArchive() throws IOException {
        try (var file = TestFileUtils.zipInputStream("simple.zip")) {
            Set<String> affectedFiles = new HashSet<>();

            var zipCaller = new ZipEntryCaller(file);
            Consumer<ZipEntryCaller.SimpleZippedFile> simpleZippedFileConsumer = f -> {
                affectedFiles.add(f.getOriginalName());
                try {
                    System.out.println(f.getOriginalName() + " -> " + Files.readString(f.getPathToFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            zipCaller.call(simpleZippedFileConsumer);

            assertEquals(3, affectedFiles.size());
        }
    }
}
