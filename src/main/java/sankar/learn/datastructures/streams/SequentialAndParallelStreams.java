package sankar.learn.datastructures.streams;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class SequentialAndParallelStreams {

    @Test
    public void createParallelStream() {
        IntStream stream = IntStream.range(1,1000);
        stream.parallel().forEach(e -> System.out.printf("%s, ", e));
    }

    /*@Test
    public void threadCountInParallelStream() {
        IntStream stream = IntStream.range(1, 1000);
        Map<String, Integer> threadWorkCount = new HashMap<>();
        stream.parallel().forEach(e -> threadWorkCount.put(Thread.currentThread().getName(), threadWorkCount.pu)
    }*/
}
