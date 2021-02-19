package sankar.learn;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class BufferUsage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BufferUsage.class);

    @After
    public void after() {
        System.out.println("<---------------------------------------------------------------->");
        System.out.println("\n");
    }
    /**
     * Java Buffer
     *
     * A container for data of a specific primitive type.
     * A buffer is a linear, finite sequence of elements of a specific primitive type. Aside from its content, the essential properties of a buffer are its capacity, limit, and position:
     *
     * Buffer can be created as direct or normal jvm buffers
     *  1. direct - uses direct vm memory
     *  2. jvm buffers - uses jvm memory, backed by array
     */
    @Test
    public void createBuffer() {
        System.out.println("Demo of Creating Buffers:");
        long initial = Runtime.getRuntime().freeMemory();
        ByteBuffer buffer = ByteBuffer.allocate(10245678);
        long end = Runtime.getRuntime().freeMemory();
        LOGGER.info("Used Memory: {} Bytes", initial - end);

        initial = Runtime.getRuntime().freeMemory();
        buffer = ByteBuffer.allocateDirect(10245678);
        end = Runtime.getRuntime().freeMemory();
        LOGGER.info("Used Memory after allocating direct buffer: {} Bytes", initial - end);
    }

    /**
     * Buffers have two types of read and write methods
     * 1. Absolute
     * 2. Relational
     */
    @Test
    public void BufferPutAndGet() {
        System.out.println("Demo of Get() and put() methods of Buffer:");
        CharBuffer buffer = CharBuffer.allocate(10);
        //relative methods wont specify explicit index, the data is added at current position of buffer
        buffer.put('a');
        buffer.put('b');
        LOGGER.info("After adding two characters using relative put() method the markers of buffer, \n position: {}, limit: {}, capacity: {}", buffer.position(), buffer.limit(), buffer.capacity());
        buffer.put(0, 'c').put(3, 'd'); //the position is not incremented
        LOGGER.info("After adding data using absolute put() method the markers of buffer, \n position: {}, limit: {}, capacity: {} \n The markers are not incremented when absolute methods are used", buffer.position(), buffer.limit(), buffer.capacity());
        //relative get methods
        buffer.position(4);
        buffer.flip(); //Need to set the buffer for next serial operation
        LOGGER.info("Relative get methods after flipping, buffer.get() : {}, buffer.get(3): {}", buffer.get(), buffer.get(3));
    }

    /**
     *  A buffer's capacity is the number of elements it contains. The capacity of a buffer is never negative and never changes.
     *  A buffer's limit is the index of the first element that should not be read or written. A buffer's limit is never negative and is never greater than its capacity.
     *  A buffer's position is the index of the next element to be read or written. A buffer's position is never negative and is never greater than its limit.
     *
     *  0 < mark < position <  limit < capacity
     */
    @Test
    public void bufferMarkers() {
        System.out.println("Demo of positional markers of Buffer:");
        CharBuffer buffer = CharBuffer.allocate(5);
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        //put() is relative write method, which increments the position after each call.
        //Now position is 3 and limit is 5 and capacity is 5
        LOGGER.info("After writing three characters to an initialized buffer the Markers of buffer are \nposition: {}, limit: {}, capacity: {}", buffer.position(), buffer.limit(), buffer.capacity());
        //And if i keep on adding characters exceeding the capacity, an overflow exception is thrown
        try {
            buffer.put('d');
            buffer.put('e');
            buffer.put('f');
        } catch (BufferOverflowException e) {
            LOGGER.warn("Adding data to buffer over its size will result in BufferOverFlowException \n position: {}, limit: {}, capacity: {}", buffer.position(), buffer.limit(), buffer.capacity(), e);
        }
        //clear will reset the markers to initial state
        //clear() does not actually erase the data in the buffer,
        //but it is named as if it did because it will most often be used in situations in which that might as well be the case
        buffer.clear();
        LOGGER.info("After calling clear() the markers of buffer are set to its initial position, but data is not deleted \n position: {}, limit: {}, capacity: {}\n" +
                "Data at buffer[0] : {}", buffer.position(), buffer.limit(), buffer.capacity(), buffer.get(0));
    }

    /**
     * Clearing, flipping, and rewinding
     * In addition to methods for accessing the position, limit, and capacity values and for marking and resetting, this class also defines the following operations upon buffers
     * clear(): makes a buffer ready for a new sequence of channel-read or relative put operations: It sets the limit to the capacity and the position to zero.
     * flip(): makes a buffer ready for a new sequence of channel-write or relative get operations: It sets the limit to the current position and then sets the position to zero.
     * rewind(): makes a buffer ready for re-reading the data that it already contains: It leaves the limit unchanged and sets the position to zero.
     *
     * A buffer's capacity is the number of elements it contains. The capacity of a buffer is never negative and never changes.
     * A buffer's limit is the index of the first element that should not be read or written. A buffer's limit is never negative and is never greater than its capacity.
     * A buffer's position is the index of the next element to be read or written. A buffer's position is never negative and is never greater than its limit.
     */
    @Test
    public void positionalMethods() {
        System.out.println("Demo of Clearing, flipping, and rewinding Buffers:");
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put('q').put('w').put('e').put('r').put('t').put('y');
        LOGGER.info("After writing 6 chars to buffer the Markers of buffer are \n position: {}, limit: {}, capacity: {}", buffer.position(), buffer.limit(), buffer.capacity());
        buffer.flip(); //Now to read from this buffer call flip()
        LOGGER.info("After flip() the Markers of buffer are \n position: {}, limit: {}, capacity: {}", buffer.position(), buffer.limit(), buffer.capacity());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        LOGGER.info("After reading 3 characters Markers of buffer are \n position: {}, limit: {}, capacity: {}", buffer.position(), buffer.limit(), buffer.capacity());
        buffer.rewind(); //rewind to reread from buffer
        LOGGER.info("After rewind() the Markers of buffer are \n position: {}, limit: {}, capacity: {}", buffer.position(), buffer.limit(), buffer.capacity());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }

}
