package pl.longhorn.gtfsrepo.calendar.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.calendar.Calendar;
import pl.longhorn.gtfsrepo.calendar.*;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;
import pl.longhorn.gtfsrepo.service.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CalendarCsvTranslator {

    private final CalendarRepository calendarRepository;
    private final ServiceRepository serviceRepository;

    public List<Calendar> translate(List<CalendarCsvModel> calendars, SchemaVersion schemaVersion) {
        List<Calendar> list = new ArrayList<>();
        Map<String, Service> serviceByExternalId = new HashMap<>();
        for (CalendarCsvModel c : calendars) {
            Service service = prepareService(serviceByExternalId, c.getExternalServiceId(), schemaVersion);
            Calendar translate = translate(c, schemaVersion, service);
            list.add(translate);
        }
        return list;
    }

    public Calendar translate(
            CalendarCsvModel csvModel,
            SchemaVersion schemaVersion,
            Service service) {
        var calendar = Calendar.builder()
                .schemaId(schemaVersion.getId())
                .serviceId(service.getId())
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

    private Service prepareService(Map<String, Service> serviceByExternalId, String externalServiceId, SchemaVersion schemaVersion) {
        var service = serviceByExternalId.get(externalServiceId);
        if (service == null) {
            service = serviceRepository.save(Service.builder()
                    .schemaId(schemaVersion.getId())
                    .externalServiceId(externalServiceId)
                    .build());
            serviceByExternalId.put(externalServiceId, service);
        }
        return service;
    }
}
