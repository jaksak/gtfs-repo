package pl.longhorn.gtfsrepo.calendardates.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.calendardates.*;
import pl.longhorn.gtfsrepo.calendardates.exception.*;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;
import pl.longhorn.gtfsrepo.service.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class CalendarDateCsvTranslator {

    private final CalendarDateRepository calendarDateRepository;
    private final CalendarDateExceptionTypeMapper calendarDateExceptionTypeMapper;

    private final ServiceService serviceService;

    public Map<String, Service> translate(List<CalendarDateCsvModel> calendarDateCsvModels, SchemaVersion schemaVersion, Map<String, Service> serviceByExternalId) {
        for (CalendarDateCsvModel csvModel : calendarDateCsvModels) {
            Service service = prepareService(csvModel.getExternalServiceId(), serviceByExternalId, schemaVersion);
            var exceptionType = calendarDateExceptionTypeMapper.map(csvModel.getExceptionTypeValue());
            var calendarDate = map(csvModel, service, schemaVersion, exceptionType);
            calendarDateRepository.save(calendarDate);
        }
        return serviceByExternalId;
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
