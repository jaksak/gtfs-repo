package pl.longhorn.gtfsrepo.calendardates.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.bundle.GtfsBundleWorkingData;
import pl.longhorn.gtfsrepo.calendardates.CalendarDate;
import pl.longhorn.gtfsrepo.calendardates.CalendarDateRepository;
import pl.longhorn.gtfsrepo.calendardates.exception.CalendarDateExceptionType;
import pl.longhorn.gtfsrepo.calendardates.exception.CalendarDateExceptionTypeMapper;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;
import pl.longhorn.gtfsrepo.service.Service;
import pl.longhorn.gtfsrepo.service.ServiceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CalendarDateCsvTranslator {

    private final CalendarDateRepository calendarDateRepository;
    private final CalendarDateExceptionTypeMapper calendarDateExceptionTypeMapper;

    private final ServiceService serviceService;

    public GtfsBundleWorkingData translate(GtfsBundleWorkingData data, SchemaVersion schemaVersion) {
        var serviceByExternalId = data.getSavedServices();
        List<CalendarDate> calendarDates = new ArrayList<>(data.getCalendarDates().size());
        for (CalendarDateCsvModel csvModel : data.getCalendarDates()) {
            Service service = prepareService(csvModel.getExternalServiceId(), serviceByExternalId, schemaVersion);
            var exceptionType = calendarDateExceptionTypeMapper.map(csvModel.getExceptionTypeValue());
            var calendarDate = map(csvModel, service, schemaVersion, exceptionType);
            calendarDates.add(calendarDateRepository.save(calendarDate));
        }
        data.setSavedServices(serviceByExternalId);
        data.setSavedCalendarDates(calendarDates);
        return data;
    }

    private CalendarDate map(CalendarDateCsvModel csvModel, Service service, SchemaVersion schemaVersion, CalendarDateExceptionType exceptionType) {
        return CalendarDate.builder()
                .schemaId(schemaVersion.getId())
                .serviceId(service.getId())
                .date(csvModel.getDate())
                .exceptionType(exceptionType)
                .build();
    }

    private Service prepareService(String externalServiceId, Map<String, Service> serviceByExternalId, SchemaVersion schemaVersion) {
        var service = serviceByExternalId.get(externalServiceId);
        if (service == null) {
            service = serviceService.findOrCreate(schemaVersion.getId(), externalServiceId);
            serviceByExternalId.put(externalServiceId, service);
        }
        return service;
    }
}
