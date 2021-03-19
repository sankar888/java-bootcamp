package sankar.learn.concurrentprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasicThreadUsage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicThreadUsage.class);

    public static void main(String[] args) {
        BasicThreadUsage demo = new BasicThreadUsage();
        //demo.createThreadsDemo();
        //demo.threadPriorityDemo();
        //demo.threadStateDemo();
        //demo.threadGroupDemo();
        //demo.threadJoinDemo();
        demo.daemonThreadDemo();
    }

    /**
     * A thread is a thread of execution in a program. The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
     * There are two ways to create a new thread of execution.
     * One is to declare a class to be a subclass of Thread. This subclass should override the run method of class Thread.
     *
     * The other way to create a thread is to declare a class that implements the Runnable interface.
     * That class then implements the run method.
     * An instance of the class can then be allocated, passed as an argument when creating Thread, and started
     */
    private void createThreadsDemo() {
        //using runnable
        Runnable countDown = new CountDownRunnable(10, Duration.ofSeconds(3));
        Thread th = new Thread(countDown, "Thread_Using_runnable");
        th.start();

        th = new CountDownThread(10, Duration.ofSeconds(3));
        th.setName("Thread_extending_thread_class");
        th.start();

        countDown = () -> {
            LOGGER.info("Thread can also be created using lambdas since java 8, Thread name: {}",Thread.currentThread().getName());
        };
        th = new Thread(countDown);
        th.setName("thread_from_lambda_runnable");
        th.start();

        System.out.println("This message will be printed in the initial phase of execution, Since [main] thread is free to proceed");
    }

    private static class CountDownThread extends Thread {
        private int countFrom;
        private final Duration pause;

        public CountDownThread(int from, Duration pause) {
            if (from <= 0) {
                throw new IllegalArgumentException("cannot countdown from zero or negative numbers");
            }
            this.countFrom = from;
            this.pause = pause;
        }

        @Override
        public void run() {
            countDownToZero(countFrom, pause);
        }
    }

    private static class CountDownRunnable implements Runnable {
        private int countFrom;
        private final Duration pause;

        public CountDownRunnable(int from, Duration pause) {
            if (from <= 0) {
                throw new IllegalArgumentException("cannot countdown from zero or negative numbers");
            }
            this.countFrom = from;
            this.pause = pause;
        }

        @Override
        public void run() {
            countDownToZero(countFrom, pause);
        }
    }

    private void threadPriorityDemo() {
        // creating using lambdas for the sake of clarity and modularity
        Runnable r1 = () -> {
            Thread th = Thread.currentThread();
            countDownToZero(5, Duration.ofSeconds(2));
            System.out.println(String.format("Thread %s with priority %d completed", th.getName(), th.getPriority()));
        };
        //same runnable, but two threads of different priority
        Thread t1 = new Thread(r1, "PRIORITY_THREAD");
        t1.setPriority(Thread.MAX_PRIORITY);

        List<Thread> threads = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            threads.add(new Thread(r1,"THREAD-"+i));
        }
        threads.add(t1);

        //start all the threads and lets see which thread complete first
        //Not working as expected, might be because ach thread is not generating enougf load or the underlying scheduler is not considering priority
        threads.forEach(Thread::start);
    }

    /**
    * A thread has an execution state that is identified by one of the Thread.State enum’s constants:
    *
    *   NEW: A thread that has not yet started is in this state.
    *   RUNNABLE: A thread executing in the JVM is in this state.
    *   BLOCKED: A thread that is blocked waiting for a monitor lock is in this state.
    *   WAITING: A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
    *   TIMED_WAITING: A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
    *   TERMINATED: A thread that has exited is in this state.
     * @see Thread.State
    */
    private void threadStateDemo() {
        Runnable r1 = () -> {
            Thread th = Thread.currentThread();
            countDownToZero(5, Duration.ofSeconds(2));
            System.out.println(String.format("Thread %s with priority %d completed", th.getName(), th.getPriority()));
        };
        Thread t1 = new Thread(r1,"Thread_state_demo");
        LOGGER.info("Before staring , thread state : {}", t1.getState().name()); //should be NEW
        t1.start();
        LOGGER.info("Just after starting thread, thread State : {}", t1.getState().name()); //should be RUNNABLE
        while (t1.isAlive()) {
            LOGGER.info("Monitoring running thread , thread state: {}", t1.getState().name()); //Since the runnable has some sleep() code, it should print RUNNABLE and TIMED_WAITING
        }
        LOGGER.info("After thread completes, thread state: {}", t1.getState().name()); //should be TERMINATED
    }

    /**
     * Every Java thread is a member of a thread group. Thread groups provide a mechanism for collecting multiple threads into a single object and manipulating those threads all at once, rather than individually.
     * For example, you can start or suspend all the threads within a group with a single method call.
     *
     * A thread group represents a set of threads. In addition, a thread group can also include other thread groups.
     * The thread groups form a tree in which every thread group except the initial thread group has a parent.
     * A thread is allowed to access information about its own thread group, but not to access information about its thread group's parent thread group or any other thread groups.
     *
     * ThreadGroup is only used for grouping and viewing purposes, use Executor api for managing multiple threads
     */
    private void threadGroupDemo() {
        Runnable r1 = () -> {
            Thread th = Thread.currentThread();
            countDownToZero(5, Duration.ofSeconds(2));
            System.out.println(String.format("Thread %s with priority %d completed", th.getName(), th.getPriority()));
        };
        ThreadGroup group = new ThreadGroup("simple_thread_group");
        ThreadGroup parent = group.getParent();
        LOGGER.info("Parent of newly created thread group is : parent {}", parent); //should be main thread group

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        LOGGER.info("The parent of newly created thread group is main thread group: {}", parent.equals(mainGroup));

        for (int i = 0; i < 10; i++) {
            new Thread(group, r1).start();
        }
        System.out.println(String.format("Active thread count of created group: %s", group.activeCount()));
        //thread group allows us to handle all threads in that group as a single entity
        //list all threads in group
        group.list();
        group.interrupt(); //should stop all running threads
        try {
            group.destroy(); //might throw IllegalThreadStateException since some threads might be still running
        } catch (IllegalThreadStateException e) {
            LOGGER.warn("Thread group destroy() gives exception", e);
        }
    }

    /**
     * The Thread class provides an interruption mechanism in which one thread can interrupt another thread. When a thread is interrupted, it throws java.lang.InterruptedException. This mechanism consists of the following three methods:
     *
     *     void interrupt(): Interrupt the thread identified by the Thread object on which this method is called. When a thread is blocked because of a call to one of Thread’s sleep() or join() methods (discussed later in this chapter), the thread’s interrupted status is cleared and InterruptedException is thrown. Otherwise, the interrupted status is set and some other action is taken depending on what the thread is doing. (See the JDK documentation for the details.)
     *     static boolean interrupted(): Test whether the current thread has been interrupted, returning true in this case. The interrupted status of the thread is cleared by this method.
     *     boolean isInterrupted(): Test whether this thread has been interrupted, returning true in this case. The interrupted status of the thread is unaffected by this method
     */
    private void threadInterruptDemo() {
        Runnable r1 = () -> {
            Thread th = Thread.currentThread();
            countDownToZero(5, Duration.ofSeconds(2));
            System.out.println(String.format("Thread %s with priority %d completed", th.getName(), th.getPriority()));
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
        t1.interrupt(); //should throw interrupted exception, interrupt is the abrupt way to stop a thread
    }

    /**
     * The join method allows one thread to wait for the completion of another. If t is a Thread object whose thread is currently executing,
     t.join();
     causes the current thread (main) to pause execution until t's thread terminates.
     Overloads of join allow the programmer to specify a waiting period. However, as with sleep, join is dependent on the OS for timing,
     so you should not assume that join will wait exactly as long as you specify.
     Like sleep, join responds to an interrupt by exiting with an InterruptedException.
     */
    private void threadJoinDemo() {
        Runnable r1 = () -> {
            Thread th = Thread.currentThread();
            countDownToZero(5, Duration.ofSeconds(2));
            System.out.println(String.format("Thread %s with priority %d completed", th.getName(), th.getPriority()));
        };
        Thread th = new Thread(r1);
        try {
            th.join(); //if thread is not started nothing happens
        } catch (InterruptedException e) {
            LOGGER.warn("Thread interrupted while waiting for completion ", e);
        }
        th.start();
        try {
            th.join(); //Will wait for the thread to complete
        } catch (InterruptedException e) {
            LOGGER.warn("Thread interrupted while waiting for completion ", e);
        }
        LOGGER.info("This message will print after the thread {}, is completed. Since join() blocks the [main] thread execution", th.getName());

        th = new Thread(r1);
        th.start();
        try {
            th.join(4*1000); //will wait for 4 secs
        } catch (InterruptedException e) {
            LOGGER.warn("Thread interrupted while waiting for completion ", e);
        }
        LOGGER.info("This message will print after 4 secs waiting for the thread {}, to completed. Since join(4000) blocks the [main] thread execution for 4 secs", th.getName());
    }

    private void daemonThreadDemo() {
        Runnable r1 = () -> {
            Thread th = Thread.currentThread();
            countDownToZero(5, Duration.ofSeconds(2));
            System.out.println(String.format("Thread %s with priority %d completed", th.getName(), th.getPriority()));
        };
        Thread th = new Thread(r1);
        th.setDaemon(true); //the jdk waits only for non-daemon threads
        th.start();
        LOGGER.info("Main execution Completed, and the execution exits");
    }

    private static void countDownToZero(int from, Duration pauseInBetween) {
        try {
            while (from != 0) {
                from--;
                System.out.println(String.format("%s counting down to zero, current count \t : %d", Thread.currentThread().getName(), from));
                Thread.sleep(pauseInBetween.toMillis());
            }
        } catch (InterruptedException e) {
            Thread.interrupted();
            LOGGER.warn("Thread {} interrupted", Thread.currentThread().getName(), e);
        }
    }
}
