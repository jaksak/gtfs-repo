package pl.longhorn.gtfsrepo.primitive;

import java.util.*;
import java.util.function.*;

public class FunctionUtils {

    public static <INPUT, RESULT> RESULT ifNotNull(INPUT input, Function<INPUT, RESULT> function) {
        return ifNotNullOrElse(input, function, () -> null);
    }

    public static <INPUT extends Collection<?>, RESULT> Collection<RESULT> ifNotEmpty(INPUT input, Function<INPUT, Collection<RESULT>> function) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        } else {
            return function.apply(input);
        }
    }

    public static <INPUT, RESULT> RESULT ifNotNullOrElse(
            INPUT input,
            Function<INPUT, RESULT> function,
            Supplier<RESULT> orElseSupplier) {
        if (input == null) {
            return orElseSupplier.get();
        } else {
            return function.apply(input);
        }
    }
}
