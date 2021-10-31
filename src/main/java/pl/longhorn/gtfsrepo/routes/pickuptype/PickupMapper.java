package pl.longhorn.gtfsrepo.routes.pickuptype;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PickupMapper {

    private final Map<Byte, PickupType> typesByBaseValue;

    public PickupMapper() {
        this.typesByBaseValue = PickupType.getAll().stream()
                .collect(Collectors.toMap(PickupType::getBaseValue, p -> p));
    }

    public PickupType map(Byte baseValue) {
        if (baseValue == null) {
            return PickupType.defaultValue();
        }
        var result = typesByBaseValue.get(baseValue);
        if (result == null) {
            throw new IllegalArgumentException(baseValue + " is invalid");
        } else {
            return result;
        }
    }
}
