package pl.longhorn.gtfsrepo.bundle;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("customer/bundle")
@RequiredArgsConstructor
public class GtfsBundleController {

    private final GtfsBundleService gtfsBundleService;

    @PostMapping
    public void uploadBundleGtfsData(
            @RequestParam("customerId") int customerId,
            @RequestParam("file") MultipartFile file) throws IOException {
        try (var inputStream = file.getInputStream()) {
            gtfsBundleService.run(inputStream, customerId);
        }
    }
}
