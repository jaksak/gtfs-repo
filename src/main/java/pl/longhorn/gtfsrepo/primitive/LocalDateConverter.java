package pl.longhorn.gtfsrepo.primitive;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractBeanField<String, LocalDate> {
    @Override
    protected LocalDate convert(String value) {
        if (value != null && !value.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            return LocalDate.parse(value, formatter);
        } else {
            return null;
        }
    }
}
