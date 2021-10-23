package pl.longhorn.gtfsrepo.trips.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TripLoaderTest {

    @Test
    void shouldParseData() {
        var loader = new TripLoader();
        var input = TestFileUtils.load("trips.txt");

        var result = loader.load(input);

        assertEquals(13_815, result.size());

        var first = result.get(0);
        assertEquals("block_7_trip_1_service_1", first.getTripId());
        assertEquals("route_942", first.getRouteId());
        assertEquals("service_1", first.getServiceId());
        assertEquals("Wzgórza K.", first.getTripHeadsign());
        assertEquals("", first.getTripShortName());
        assertEquals(ByteUtils.asByte(0), first.getDirectionId());
        assertEquals("block_7", first.getBlockId());
        assertEquals("", first.getShapeId());
        assertNull(first.getWheelchairAccessible());
    }
}
