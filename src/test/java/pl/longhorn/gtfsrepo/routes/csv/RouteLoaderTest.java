package pl.longhorn.gtfsrepo.routes.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteLoaderTest {

    @Test
    void shouldParseData() {
        var loader = new RouteLoader();
        var input = TestFileUtils.csvReader("routes.txt");

        var result = loader.load(input);

        assertEquals(27, result.size());

        var first = result.get(0);
        assertEquals("route_31", first.getRouteId());
        assertEquals("agency_1", first.getAgencyId());
        assertEquals("1", first.getRouteShortName());
        assertEquals("", first.getRouteLongName());
        assertEquals("", first.getRouteDesc());
        assertEquals(900, first.getRouteTypeValue());
        assertEquals("", first.getRouteUrl());
        assertEquals("", first.getRouteColor());
        assertEquals("", first.getRouteTextColor());
    }
}
