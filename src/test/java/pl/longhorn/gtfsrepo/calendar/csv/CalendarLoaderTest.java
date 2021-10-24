package pl.longhorn.gtfsrepo.calendar.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarLoaderTest {

    @Test
    void shouldLoadData() {
        var loader = new CalendarLoader();
        var input = TestFileUtils.csvReader("calendar.txt");

        var result = loader.load(input);

        assertEquals(4, result.size());

        var first = result.get(0);
        assertTrue(first.isMonday());
        assertFalse(first.isFriday());
        assertEquals(LocalDate.of(2021, 10, 1), first.getStartDate());
        assertEquals(LocalDate.of(2021, 11, 30), first.getEndDate());
    }
}
