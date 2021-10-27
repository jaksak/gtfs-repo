package pl.longhorn.gtfsrepo.service;

import lombok.*;

@Data
@Builder
public class Service {
    private int id;
    private int schemaId;
    private String name;
    private String externalServiceId;
}
