package pl.longhorn.gtfsrepo.stoptimes.csv;

import com.opencsv.bean.*;
import lombok.Data;
import pl.longhorn.gtfsrepo.primitive.DurationConverter;

import java.time.Duration;

@Data
public class StopTimeCsvModel {
    @CsvBindByName(column = "trip_id")
    private String tripId;
    @CsvCustomBindByName(column = "arrival_time", converter = DurationConverter.class)
    private Duration arrivalTime;
    @CsvCustomBindByName(column = "departure_time", converter = DurationConverter.class)
    private Duration departureTime;
    @CsvBindByName(column = "stop_id")
    private String stopId;
    @CsvBindByName(column = "stop_sequence")
    private int stopSequence;
    @CsvBindByName(column = "stop_headsign")
    private String stopHeadsign;
    @CsvBindByName(column = "pickup_type")
    private Byte pickupType;
    @CsvBindByName(column = "drop_off_type")
    private Byte dropOffType;
    @CsvBindByName(column = "continuous_pickup")
    private Byte continuousPickup;
    @CsvBindByName(column = "continuous_drop_off")
    private Byte continuousDropOff;
    @CsvBindByName(column = "shape_dist_traveled")
    private String shapeDistTraveled;
    @CsvBindByName(column = "timepoint")
    private Byte timepoint;
}
