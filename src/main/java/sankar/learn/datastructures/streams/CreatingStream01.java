package sankar.learn.datastructures.streams;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Streams can be obtained in a number of ways. Some examples include:
 *
 *     From a Collection via the stream() and parallelStream() methods;
 *     From an array via Arrays.stream(Object[]);
 *     From static factory methods on the stream classes, such as Stream.of(Object[]), IntStream.range(int, int) or Stream.iterate(Object, UnaryOperator);
 *     The lines of a file can be obtained from BufferedReader.lines();
 *     Streams of file paths can be obtained from methods in Files;
 *     Streams of random numbers can be obtained from Random.ints();
 *     Numerous other stream-bearing methods in the JDK, including BitSet.stream(), Pattern.splitAsStream(java.lang.CharSequence), and JarFile.stream().
 *
 * Additional stream sources can be provided by third-party libraries using these techniques.
 */
public class CreatingStream01 {

    @Test
    public void createStreamFromCollections() {
        int[] arr = {1,2,3};
        IntStream ints = Arrays.stream(arr);
    }
}
