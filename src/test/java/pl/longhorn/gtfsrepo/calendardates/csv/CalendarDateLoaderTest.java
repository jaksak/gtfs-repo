package pl.longhorn.gtfsrepo.calendardates.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalendarDateLoaderTest {

    @Test
    void shouldParseData() {
        var loader = new CalendarDateLoader();
        var input = TestFileUtils.csvReader("calendar_dates.txt");

        var result = loader.load(input);

        assertEquals(4, result.size());

        var first = result.get(0);
        assertEquals("service_1", first.getExternalServiceId());
        assertEquals(LocalDate.of(2021, 11, 1), first.getDate());
        assertEquals(2, first.getExceptionTypeValue());
    }
}
