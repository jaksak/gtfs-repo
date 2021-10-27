package pl.longhorn.gtfsrepo.feedinfo.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.feedinfo.*;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FeedInfoCsvTranslator {

    private final FeedInfoRepository feedInfoRepository;

    public void translate(List<FeedInfoCsvModel> feedInfoCsvModels, SchemaVersion schemaVersion) {
        feedInfoCsvModels.forEach(f -> translate(f, schemaVersion));
    }

    public void translate(FeedInfoCsvModel csvModel, SchemaVersion schemaVersion) {
        var feedInfo = map(csvModel, schemaVersion);
        feedInfoRepository.save(feedInfo);
    }

    private FeedInfo map(FeedInfoCsvModel csvModel, SchemaVersion schemaVersion) {
        return FeedInfo.builder()
                .schemaId(schemaVersion.getId())
                .feedPublisherName(csvModel.getFeedPublisherName())
                .feedPublisherUrl(csvModel.getFeedPublisherUrl())
                .defaultLang(csvModel.getDefaultLang())
                .feedContactEmail(csvModel.getFeedContactEmail())
                .feedContactUrl(csvModel.getFeedContactUrl())
                .feedLang(csvModel.getFeedLang())
                .feedVersion(csvModel.getFeedVersion())
                .feedStartDate(csvModel.getFeedStartDate())
                .feedEndDate(csvModel.getFeedEndDate())
                .build();
    }
}
