package pl.longhorn.gtfsrepo.bundle;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.longhorn.gtfsrepo.zip.ZipEntryCaller;

import java.io.IOException;

@RestController
@RequestMapping("customer/bundle")
public class GtfsBundleController {

    @PostMapping
    public void uploadBundleGtfsData(@RequestParam("file") MultipartFile file) throws IOException {
        try (var inputStream = file.getInputStream()) {
            new ZipEntryCaller(inputStream).call(f -> System.out.println(f.getOriginalName()));
        }
    }
}
