package pl.longhorn.gtfsrepo.routes.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class RouteCsvModel {

    @CsvBindByName(column = "route_id")
    private String routeId;
    @CsvBindByName(column = "agency_id")
    private String agencyId;
    @CsvBindByName(column = "route_short_name")
    private String routeShortName;
    @CsvBindByName(column = "route_long_name")
    private String routeLongName;
    @CsvBindByName(column = "route_desc")
    private String routeDesc;
    @CsvBindByName(column = "route_type")
    private short routeTypeValue;
    @CsvBindByName(column = "route_url")
    private String routeUrl;
    @CsvBindByName(column = "route_color")
    private String routeColor;
    @CsvBindByName(column = "route_text_color")
    private String routeTextColor;
    @CsvBindByName(column = "route_sort_order")
    private Integer routeSortOrder;
    @CsvBindByName(column = "continuous_pickup")
    private Byte continuousPickupValue;
    @CsvBindByName(column = "continuous_drop_off")
    private Byte continuousDropOffValue;
}
