package sankar.learn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalUsage {
    /**
     * https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
     * A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value
     * https://dzone.com/articles/using-optional-correctly-is-not-optional
     */
    @Test
    public void createAnOptional() {
        Optional<String> value = Optional.of("string"); //throws NullPointerException if the input to of() method is NULL
        Optional<String> valueCanBeBull = Optional.ofNullable(null); //can accomodate NULL values
        Optional<String> empty = Optional.empty();

        Assertions.assertEquals("string", value.get()); //should give the value, since this optional holds value
        Assertions.assertEquals("string", value.orElse("other"));

        Assertions.assertThrows(NoSuchElementException.class, () -> { //if optional holds null value it throws this exception, so we should be always use isPresent() to check if the optional holds any value
            valueCanBeBull.get();
        });
        Assertions.assertEquals("other", valueCanBeBull.orElse("other"));
        Assertions.assertFalse(valueCanBeBull.isPresent()); //since there is no value contained in optional

        Assertions.assertThrows(NoSuchElementException.class, () -> {//since there is no value in empty optional optional it throws NoSuchElementException
           empty.get();
        });
        Assertions.assertEquals("other", empty.orElse("other"));
    }

    /**
     * Optional is intended to be used with java stream api and not in method parameters or return values or getter or setter
     * https://dzone.com/articles/using-optional-correctly-is-not-optional
     */
    @Test
    public void optionalUsageInStreams() {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        Optional<Integer> negativeValue = ints.stream()
                .filter(i -> i < 0)
                .findFirst(); //since te filter may or may not filter out a value, a container like optional is necessary to be used in streams api
        Assertions.assertEquals(0, negativeValue.orElse(0)); //since there is no negative value o will be given
        Assertions.assertThrows(NoSuchElementException.class, negativeValue::get);
        Assertions.assertFalse(negativeValue.isPresent());
    }
}
