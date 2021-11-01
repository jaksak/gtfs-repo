package pl.longhorn.gtfsrepo.calendar.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.bundle.GtfsBundleWorkingData;
import pl.longhorn.gtfsrepo.calendar.Calendar;
import pl.longhorn.gtfsrepo.calendar.CalendarRepository;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;
import pl.longhorn.gtfsrepo.service.Service;
import pl.longhorn.gtfsrepo.service.ServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CalendarCsvTranslator {

    private final CalendarRepository calendarRepository;
    private final ServiceService serviceService;

    public GtfsBundleWorkingData translate(GtfsBundleWorkingData data) {
        List<Calendar> calendars = new ArrayList<>();
        Map<String, Service> serviceByExternalId = data.getSavedServices();
        for (CalendarCsvModel c : data.getCalendar()) {
            Service service = prepareService(serviceByExternalId, c.getExternalServiceId(), data.getSchemaVersion());
            Calendar translate = translate(c, data.getSchemaVersion(), service);
            calendars.add(translate);
        }
        data.setSavedCalendar(calendars);
        data.setSavedServices(serviceByExternalId);
        return data;
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
            service = serviceService.findOrCreate(schemaVersion.getId(), externalServiceId);
            serviceByExternalId.put(externalServiceId, service);
        }
        return service;
    }
}
