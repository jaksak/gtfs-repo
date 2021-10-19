package pl.longhorn.gtfsrepo.feedinfo.csv;

import org.junit.jupiter.api.Test;
import pl.longhorn.gtfsrepo.TestFileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FeedInfoLoaderTest {

    @Test
    void shouldParseData() {
        var loader = new FeedInfoLoader();
        var input = TestFileUtils.load("feed_info.txt");

        var result = loader.load(input);

        assertEquals(1, result.size());

        var first = result.get(0);
        assertEquals("ZTP", first.getFeedPublisherName());
        assertEquals("http://ztp.krakow.pl/", first.getFeedPublisherUrl());
        assertEquals("pl", first.getFeedLang());
        assertNull(first.getFeedStartDate());
        assertNull(first.getFeedEndDate());
        assertEquals("01.10.2021 10:11", first.getFeedVersion());
    }
}
