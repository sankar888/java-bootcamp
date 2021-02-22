package sankar.learn.concurrentprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class DiningPhilosophersProblem {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiningPhilosophersProblem.class);

    private static class Philosopher implements Runnable {
        private final int position;
        private long eaten = 0;

        public Philosopher(int position) {
            this.position = position;
        }

        @Override
        public void run() {
            //TODO: complete the exercise after learning the thread basics
        }
    }


    private static class ChopStick {

    }


}
