package pl.longhorn.gtfsrepo.customer;

import lombok.*;

@Data
@Builder
public class Customer {
    private int id;
    private String name;
}
