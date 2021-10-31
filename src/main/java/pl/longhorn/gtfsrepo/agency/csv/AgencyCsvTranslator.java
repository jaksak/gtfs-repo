package pl.longhorn.gtfsrepo.agency.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.agency.Agency;
import pl.longhorn.gtfsrepo.agency.AgencyRepository;
import pl.longhorn.gtfsrepo.bundle.GtfsBundleWorkingData;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgencyCsvTranslator {

    private final AgencyRepository agencyRepository;

    public GtfsBundleWorkingData translate(GtfsBundleWorkingData data, SchemaVersion schemaVersion) {
        var agencies = data.getAgencyCsv().stream()
                .map(m -> translate(m, schemaVersion))
                .collect(Collectors.toMap(Agency::getAgencyExternalId, a -> a));
        data.setSavedAgencies(agencies);
        return data;
    }

    public Agency translate(AgencyCsvModel csvModel, SchemaVersion schemaVersion) {
        var agency = map(csvModel, schemaVersion);
        return agencyRepository.save(agency);
    }

    private Agency map(AgencyCsvModel csvModel, SchemaVersion schemaVersion) {
        return Agency.builder()
                .schemaId(schemaVersion.getId())
                .agencyExternalId(csvModel.getAgencyId())
                .agencyName(csvModel.getAgencyName())
                .agencyUrl(csvModel.getAgencyUrl())
                .agencyTimezone(csvModel.getAgencyTimezone())
                .agencyLang(csvModel.getAgencyLang())
                .agencyPhone(csvModel.getAgencyPhone())
                .agencyFareUrl(csvModel.getAgencyFareUrl())
                .agencyEmail(csvModel.getAgencyEmail())
                .build();
    }
}
