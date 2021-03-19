package sankar.learn.concurrentprogramming;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophersProblem {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiningPhilosophersProblem.class);
    private final int numOfPhilosophers;
    private final List<ChopStick> chopSticks = new ArrayList<>();
    private final List<Philosopher> philosophers = new ArrayList<>();
    private final ExecutorService executors;
    private final long[] score;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        DiningPhilosophersProblem game = new DiningPhilosophersProblem(5);
        game.startDining();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.stopDining();
    }

    public DiningPhilosophersProblem(int numOfPhilosophers) {
        Preconditions.checkArgument(numOfPhilosophers > 1, "This game is played with atleast two players");
        this.numOfPhilosophers = numOfPhilosophers;
        this.executors = Executors.newFixedThreadPool(this.numOfPhilosophers);
        this.score = new long[numOfPhilosophers];
        this.init();
    }

    private void init() {
        for (int i=0; i < numOfPhilosophers; i++) {
            chopSticks.add(i, new ChopStick(i));
        }
        for (int i=0; i < numOfPhilosophers; i++) {
            int right = (i+1 == numOfPhilosophers) ? 0 : i+1;
            Philosopher p = new Philosopher(i, chopSticks.get(i), chopSticks.get(right));
            philosophers.add(i, p);
        }
    }

    public void startDining() {
        philosophers.forEach(executors::submit);
    }

    public void stopDining() {
        executors.shutdown();
        System.out.println("Philosophers have eaten"+ Arrays.asList(score));
    }

    private class Philosopher implements Runnable {
        private final int position;
        private final ChopStick left;
        private final ChopStick right;
        private long eaten = 0;

        public Philosopher(int position, ChopStick left, ChopStick right) {
            this.position = position;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            boolean leftLocked = false;
            while(!Thread.interrupted()) {
                right.lock();
                leftLocked = left.tryLock();
                    if (leftLocked) {
                        try {
                            LOGGER.info("Philosopher-{} eating", position);
                            Thread.sleep(2000); //eat for 10 secs
                            ++eaten;
                            LOGGER.info("Philosopher-{} done eating . eaten: {}", position, eaten);
                            lock.lock();
                            score[position] = eaten;
                            lock.unlock();
                        } catch (InterruptedException e) {
                            LOGGER.error("Thread {} interrupted while eating", Thread.currentThread().getName(), e);
                        } finally {
                            right.unlock();
                            left.unlock();
                        }
                    } else {
                        right.unlock();
                    }
                    if (leftLocked) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            LOGGER.error("Thread {} interrupted while waiting", Thread.currentThread().getName(), e);
                        }
                    }
                }
            }

        }


    private static class ChopStick extends ReentrantLock {
        private int index = 0;
        public ChopStick(int index) {
            super();
            this.index = index;
        }

        @Override
        public String toString() {
            return "ChopStick{" +
                    "index=" + index +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ChopStick)) return false;
            ChopStick chopStick = (ChopStick) o;
            return index == chopStick.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }
}
