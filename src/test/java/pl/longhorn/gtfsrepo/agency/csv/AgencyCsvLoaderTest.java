package pl.longhorn.gtfsrepo.agency.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgencyCsvLoaderTest {

    @Test
    public void shouldLoadData() {
        var loader = new AgencyCsvLoader();
        var input = TestFileUtils.csvReader("agency.txt");

        var result = loader.load(input);

        assertEquals(1, result.size());

        var first = result.get(0);
        assertEquals("agency_1", first.getAgencyId());
        assertEquals("ZTP  Kraków", first.getAgencyName());
        assertEquals("http://ztp.krakow.pl/", first.getAgencyUrl());
        assertEquals("Europe/Warsaw", first.getAgencyTimezone());
        assertEquals("+48 12 19 150", first.getAgencyPhone());
    }
}
