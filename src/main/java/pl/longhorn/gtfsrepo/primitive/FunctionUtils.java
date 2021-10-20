package pl.longhorn.gtfsrepo.primitive;

import java.util.function.Function;

public class FunctionUtils {

    public <INPUT, RESULT> RESULT ifNotNull(INPUT input, Function<INPUT, RESULT> function) {
        if (input == null) {
            return null;
        } else {
            return function.apply(input);
        }
    }
}
