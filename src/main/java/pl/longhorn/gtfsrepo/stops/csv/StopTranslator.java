package pl.longhorn.gtfsrepo.stops.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.longhorn.gtfsrepo.bundle.GtfsBundleWorkingData;
import pl.longhorn.gtfsrepo.schemaversion.SchemaVersion;
import pl.longhorn.gtfsrepo.stops.Stop;
import pl.longhorn.gtfsrepo.stops.StopsRepository;
import pl.longhorn.gtfsrepo.stops.locationtype.LocationTypeMapper;
import pl.longhorn.gtfsrepo.stops.wheelchairboarding.WheelchairBoardingMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StopTranslator {

    private final StopsRepository stopsRepository;
    private final LocationTypeMapper locationTypeMapper;
    private final WheelchairBoardingMapper wheelchairBoardingMapper;

    public GtfsBundleWorkingData translate(GtfsBundleWorkingData data) {
        List<Stop> stops = new ArrayList<>();
        Map<Integer, String> externalParentStationIdByStopId = new HashMap<>();
        Map<String, Integer> stopIdByExternalId = new HashMap<>();
        for (StopCsvModel csvModel : data.getStops()) {
            Stop stop = map(csvModel, data.getSchemaVersion());
            stop = stopsRepository.save(stop);
            String parentStation = csvModel.getParentStation();
            stopIdByExternalId.put(stop.getStopExternalId(), stop.getId());
            if (parentStation != null && !parentStation.isBlank()) {
                externalParentStationIdByStopId.put(stop.getId(), parentStation);
            }
            stops.add(stop);
        }
        externalParentStationIdByStopId.forEach((key, value) -> stopsRepository.updateParentStation(key, stopIdByExternalId.get(value)));
        data.setSavedStops(stops);
        return data;
    }

    private Stop map(StopCsvModel csvModel, SchemaVersion schemaVersion) {
        return Stop.builder()
                .schemaId(schemaVersion.getId())
                .stopExternalId(csvModel.getStopExternalId())
                .stopCode(csvModel.getStopCode())
                .stopName(csvModel.getStopName())
                .stopDesc(csvModel.getStopDesc())
                .stopLat(csvModel.getStopLat())
                .stopLon(csvModel.getStopLon())
                .zoneId(csvModel.getZoneId())
                .stopUrl(csvModel.getStopUrl())
                .locationType(locationTypeMapper.map(csvModel.getLocationType()))
                .stopTimezone(csvModel.getStopTimezone())
                .wheelchairBoarding(wheelchairBoardingMapper.map(csvModel.getWheelchairBoarding()))
                .levelId(csvModel.getLevelId())
                .platformCode(csvModel.getPlatformCode())
                .build();
    }
}
