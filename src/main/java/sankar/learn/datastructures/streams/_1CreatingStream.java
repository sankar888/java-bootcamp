package sankar.learn.datastructures.streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static sankar.learn.Utils.end;
import static sankar.learn.Utils.newLine;
import static sankar.learn.Utils.heading;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
public class _1CreatingStream {

    private static final String TEST_FILE = "src/main/resources/test_file_for_streams.txt";
    private static final String RESOURCE_DIR = "src/main/resources";

    /**
     * Streams can be created from many sources.
     * We are creating streams from array
     */
    @Test
    public void createStreamFromArray() {
        heading("Creating Streams from Array");
        int[] arr = {0,1,2,3};
        IntStream ints = Arrays.stream(arr);
        ints.filter(e -> e > 0).forEach(System.out::print); //prints 123
        end();
    }

    /**
     * Streams are AutoClosable. Streams can be used with try-catch loop
     * Usually Streams obtained from collections and arrays doesn't need close() method to be called
     * Close() method is needed to close the streams obtained from I/O channels like file, network channel etc..
     */
    @Test
    public void streamsAreAutoClosable() throws IOException {
        heading("ExploringStreams AutoClose and onClose Features");
        try(
                BufferedReader reader = Files.newBufferedReader(Paths.get(TEST_FILE));
                Stream<String> stream = reader.lines()
                .map(
                        line -> line.concat(String.valueOf(line.length()))
                );
        ) {
            stream.onClose(() -> System.out.println("onClose methods are executed when close() is called"))
                    .onClose(() -> System.out.println("we can have multiple onClose() methods, the order of execution is the order in which it is added"))
                    .onClose(() -> { int a = 1/0; })
                    .onClose(() -> System.out.println("All onClose() hooks are executed even if one throws exception"))
                    .forEach(System.out::println);
        }
        end();
    }

    /**
     * Once stream pipeline is executed by a terminal operation it cannot be used again.
     * We need to get a new Stream from source to traverse it again
     */
    @Test
    public void streamsCanBeExecutedOnlyOnce() {
        heading("Streams once Executed Cannot be used");
        IntStream stream = IntStream.range(0, 10);
        stream.filter(e -> e > 0).forEach(System.out::print); //stream executed

        Assertions.assertThrows(IllegalStateException.class, () -> {//throws java.lang.IllegalStateException
            stream.forEach(System.out::print);
        });
        end();
    }

    /**
     * Streams can be created from any collection
     * we have two methods <code>stream()</code> and <code>parallelStream()</code> with default implementation in Collection Interface
     */
    @Test
    public void createStreamsFromCollection() {
        heading("Streams can be obtained from any Collection");
        //Collection subinterface - List, Set, Queue, Dequeue
        Collection<String> collection = new LinkedList<>();
        collection.add("list");
        collection.add("of");
        collection.add("words");
        collection.stream().forEach(words -> System.out.printf("%s\t", words));
        newLine();

        Map<String, String> map = new Hashtable<>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        map.entrySet().stream().forEach(entry -> System.out.printf("%s : %s\t", entry.getKey(), entry.getValue()));
        end();
    }

    /**
     * Streams can be created using factory methods present in <code>Stream</code> class and <code>IntStream</code> class
     */
    @Test
    public void createStreamUsingFactoryMethods() {
        heading("Stream can be obtained from factory methods in Stream.java");
        //stream can be obtained from .. inputs
        Stream<Integer> stream0 = Stream.of(-1,-2,-4,-5);

        //stream can be obtained from supplier function
        Supplier<Integer> intSupplier = new Supplier<Integer>() {
            private int i = 0;
            @Override
            public Integer get() {
                return ++i;
            }
        };
        Stream<Integer> stream1 = Stream.generate(intSupplier); //infinite stream

        //stream can also be obtained by merging two stream
        Stream<Integer> combined = Stream.concat(stream0, stream1); //stream0 elements followed by stream1
        combined.limit(10).forEach(System.out::print);
        newLine();

        //Stream can be custom build using a builder
        Stream.Builder<Character> builder = Stream.builder();
        builder = builder.add('c')
                .add('u')
                .add('s')
                .add('t')
                .add('o');
        builder.accept('m');
        builder.accept('!');
        Stream<Character> customStream = builder.build();
        customStream.forEach(ch -> System.out.printf("%s\t", ch));
        end();
    }

    /**
     * Streams of file paths can be obtained from methods in Files;
     * @throws IOException
     */
    @Test
    public void createStreamFromPath() throws IOException {
        heading("Files class gives stream of paths in a directory");
        Path resourceDir = Paths.get(RESOURCE_DIR);
        try(Stream<Path> pathStream = Files.list(resourceDir)) {
            pathStream.forEach(System.out::println);
        }
        end();
    }

    /**
     * Streams of random numbers can be obtained from Random.ints();
     */
    @Test
    public void createStreamFromRandom() {
        heading("Infinite Stream from Random");
        Random r = new Random();
        IntStream intStream = r.ints(); //infinite stream
        intStream.limit(10).forEach(i -> System.out.printf("%s\t", i));
        end();
    }

    //TODO: low level stream construction using  StreamSupport

}
