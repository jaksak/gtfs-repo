package pl.longhorn.gtfsrepo.stoptimes.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StopTimeLoaderTest {

    @Test
    void shouldParseData() {
        var loader = new StopTimeLoader();
        var input = TestFileUtils.load("stop_times.txt");

        var result = loader.load(input);

        assertEquals(9, result.size());

        var first = result.get(0);
        assertEquals("block_7_trip_1_service_1", first.getTripId());
        assertEquals(Duration.parse("PT4H59M"), first.getArrivalTime());
        assertEquals(Duration.parse("PT29H6M"), first.getDepartureTime());
        assertEquals("stop_272_46429", first.getStopId());
        assertEquals(2, first.getStopSequence());
        assertEquals("", first.getStopHeadsign());
        assertEquals(ByteUtils.asByte(1), first.getPickupType());
        assertEquals(ByteUtils.asByte(0), first.getDropOffType());
        assertEquals("", first.getShapeDistTraveled());
        assertEquals(ByteUtils.asByte(1), first.getTimepoint());
    }
}
