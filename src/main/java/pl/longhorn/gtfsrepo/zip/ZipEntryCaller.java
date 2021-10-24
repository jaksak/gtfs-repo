package pl.longhorn.gtfsrepo.zip;

import lombok.*;

import java.io.*;
import java.nio.file.*;
import java.util.function.Consumer;
import java.util.zip.*;

@RequiredArgsConstructor
public class ZipEntryCaller {

    private final InputStream inputStream;

    public void call(Consumer<SimpleZippedFile> consumer) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            for (ZipEntry entry; (entry = zipInputStream.getNextEntry()) != null; ) {
                if (!entry.isDirectory()) {
                    Path filePath = Files.createTempFile("", ".txt");
                    try {
                        Files.copy(zipInputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                        consumer.accept(new SimpleZippedFile(
                                entry.getName(),
                                filePath
                        ));
                    } finally {
                        Files.deleteIfExists(filePath);
                    }
                }
                // ignore directiories
                // Files.createDirectories(resolvedPath);

            }
            zipInputStream.closeEntry();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static
    class SimpleZippedFile {
        private final String originalName;
        private final Path pathToFile;

    }
}
