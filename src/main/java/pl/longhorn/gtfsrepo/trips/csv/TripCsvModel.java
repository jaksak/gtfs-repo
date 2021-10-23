package pl.longhorn.gtfsrepo.trips.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class TripCsvModel {
    @CsvBindByName(column = "route_id")
    private String routeId;
    @CsvBindByName(column = "service_id")
    private String serviceId;
    @CsvBindByName(column = "trip_id")
    private String tripId;
    @CsvBindByName(column = "trip_headsign")
    private String tripHeadsign;
    @CsvBindByName(column = "trip_short_name")
    private String tripShortName;
    @CsvBindByName(column = "direction_id")
    private Byte directionId;
    @CsvBindByName(column = "block_id")
    private String blockId;
    @CsvBindByName(column = "shape_id")
    private String shapeId;
    @CsvBindByName(column = "wheelchair_accessible")
    private Byte wheelchairAccessible;
    @CsvBindByName(column = "bikes_allowed")
    private Byte bikesAllowed;
}
