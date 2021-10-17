package pl.longhorn.gtfsrepo.schemaversion;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class SchemaVersion {
    private int id;
    private int customerId;
    private LocalDateTime createdTime;
    private boolean isActive;
}
