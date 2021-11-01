package pl.longhorn.gtfsrepo.feedinfo.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.bundle.GtfsBundleWorkingData;
import pl.longhorn.gtfsrepo.feedinfo.FeedInfo;
import pl.longhorn.gtfsrepo.feedinfo.FeedInfoRepository;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FeedInfoCsvTranslator {

    private final FeedInfoRepository feedInfoRepository;

    public GtfsBundleWorkingData translate(GtfsBundleWorkingData data) {
        var savedFeedInfos = data.getFeedInfos().stream()
                .map(f -> translate(f, data.getSchemaVersion()))
                .collect(Collectors.toList());
        data.setSavedFeedInfos(savedFeedInfos);
        return data;
    }

    public FeedInfo translate(FeedInfoCsvModel csvModel, SchemaVersion schemaVersion) {
        var feedInfo = map(csvModel, schemaVersion);
        return feedInfoRepository.save(feedInfo);
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
