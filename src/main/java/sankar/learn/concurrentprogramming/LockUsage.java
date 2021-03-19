package sankar.learn.concurrentprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUsage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockUsage.class);

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        Runnable r1 = () -> {
            boolean locked = false;
            while (true) {
                locked = lock.tryLock();
                try {
                    if(locked) {
                        LOGGER.info("Locked, hold count : {}, queue: {}", lock.getHoldCount(), lock.getQueueLength());
                    }
                } finally {
                    if (locked) {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(r1, "Thread"+i).start();
        }
    }

}
