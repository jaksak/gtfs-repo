package pl.longhorn.gtfsrepo.agency.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.agency.*;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgencyCsvTranslator {

    private final AgencyRepository agencyRepository;

    public List<Agency> translate(List<AgencyCsvModel> csvModels, SchemaVersion schemaVersion) {
        return csvModels.stream()
                .map(m -> translate(m, schemaVersion))
                .collect(Collectors.toList());
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
