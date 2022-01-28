package sankar.learn.datastructures.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LearningMap {

    /**
     * Map interface introduced an bunch of new compute methods in 1.8 version
     * default V 	compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)
     * Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).
     * default V 	computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)
     * If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.
     * default V 	computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)
     * If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.
     * default void 	forEach(BiConsumer<? super K,? super V> action)
     * Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.
     */
    @Test
    public void computeMethodsOfMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "val1");
        map.put("key2", "val2");

        map.computeIfAbsent("key3", k -> {
            return "val3";
        });

        map.computeIfAbsent("key3", k -> "val3.0");
        map.merge("key3", "vnew", (old, newV) -> {
           System.out.printf("Runs only if the key is present with ot null value %s ---- %s", old, newV);
           return "Oldnew";
        });
        System.out.println(map);

    }
}
