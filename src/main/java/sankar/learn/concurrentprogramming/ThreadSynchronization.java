package sankar.learn.concurrentprogramming;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSynchronization {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadSynchronization.class);
    private AtomicInteger safeInt = new AtomicInteger(0);
    private int unsafeInt = 0;
    private Runnable safe = () -> {
        for (int i = 0; i < 1000; i++) {
            safeInt.incrementAndGet();
        }
    };
    private Runnable unsafe = () -> {
        for (int i = 0; i < 1000; i++) {
            ++unsafeInt;
        }
    };
    private Runnable waitFor10Secs = () -> {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            LOGGER.warn("Thread {} interrupted", Thread.currentThread().getName());
        }
    };


    @Test
    public void nonSynchronizationDemo() throws InterruptedException {
        unsafeInt = 0;
        LOGGER.info("Value of the shared memory i before  {}", unsafeInt);
        for (int i =0; i < 10; i++) {
            new Thread(unsafe).start();
        }
        Thread wait = new Thread(waitFor10Secs);
        wait.start();
        wait.join();
        LOGGER.info("Value of the shared memory i after {}", unsafeInt);
    }

    @Test
    public void synchronizationDemo() throws InterruptedException {
        safeInt.set(0);
        LOGGER.info("Value of the shared memory i before  {}", safeInt);
        for (int i =0; i < 10; i++) {
            new Thread(safe).start();
        }
        Thread wait = new Thread(waitFor10Secs);
        wait.start();
        wait.join();
        LOGGER.info("Value of the shared memory i after {}", safeInt);
    }
    //todo : comment on methods and design the solution for dining program

}
