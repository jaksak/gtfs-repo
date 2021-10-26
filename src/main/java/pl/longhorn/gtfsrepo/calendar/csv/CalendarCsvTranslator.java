package pl.longhorn.gtfsrepo.calendar.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.calendar.*;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CalendarCsvTranslator {

    private final CalendarRepository calendarRepository;

    public List<Calendar> translate(List<CalendarCsvModel> calendars, SchemaVersion schemaVersion) {
        return calendars.stream()
                .map(c -> translate(c, schemaVersion))
                .collect(Collectors.toList());
    }

    public Calendar translate(CalendarCsvModel csvModel, SchemaVersion schemaVersion) {
        var calendar = Calendar.builder()
                .schemaId(schemaVersion.getId())
                .externalServiceId(csvModel.getExternalServiceId())
                .monday(csvModel.isMonday())
                .tuesday(csvModel.isTuesday())
                .wednesday(csvModel.isWednesday())
                .thursday(csvModel.isThursday())
                .friday(csvModel.isFriday())
                .saturday(csvModel.isSaturday())
                .sunday(csvModel.isSunday())
                .startDate(csvModel.getStartDate())
                .endDate(csvModel.getEndDate())
                .build();
        return calendarRepository.save(calendar);
    }
}
