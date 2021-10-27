package pl.longhorn.gtfsrepo.service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public Service findOrCreate(int schemaId, String externalServiceId) {
        var service = serviceRepository.findBySchemaIdAndExternalServiceId(schemaId, externalServiceId);
        if (service == null) {
            service = Service.builder()
                    .schemaId(schemaId)
                    .externalServiceId(externalServiceId)
                    .build();
            return serviceRepository.save(service);
        } else {
            return service;
        }
    }
}
