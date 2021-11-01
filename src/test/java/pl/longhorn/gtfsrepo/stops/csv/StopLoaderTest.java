package pl.longhorn.gtfsrepo.stops.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;
import pl.longhorn.gtfsrepo.primitive.ByteUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StopLoaderTest {

    @Test
    void shouldParseData() {
        var loader = new StopLoader();
        var input = TestFileUtils.csvReader("stops.txt");

        var result = loader.load(input);

        assertEquals(327, result.size());

        var first = result.get(0);
        assertEquals("stop_183_6119", first.getStopExternalId());
        assertEquals("", first.getStopCode());
        assertEquals("Bratysławska", first.getStopName());
        assertEquals("", first.getStopDesc());
        assertEquals("50.08373", first.getStopLat());
        assertEquals("19.93416", first.getStopLon());
        assertEquals("", first.getZoneId());
        assertEquals("", first.getStopUrl());
        assertEquals(ByteUtils.asByte(0), first.getLocationType());
        assertEquals("", first.getParentStation());
        assertEquals("", first.getStopTimezone());
        assertNull(first.getWheelchairBoarding());

    }
}
