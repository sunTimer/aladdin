package lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;

public class DeadLock extends Thread {
    private String frs;
    private String second;
    public DeadLock(String name, String frs, String second) {
        super(name);
        this.frs = frs;
        this.second = second;
    }
    public  void run() {
        synchronized (frs) {
            System.out.println(this.getName() + " obtained: " + frs);
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLock t1 = new DeadLock("Thread1", lockA, lockB);
        DeadLock t2 = new DeadLock("Thread2", lockB, lockA);
        t1.start();
        t2.start();

        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();

        ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(deadlockedThreads);
        for (ThreadInfo info : threadInfo) {
            System.out.println(info.getThreadId() + "-" + info.getThreadName() + "-" + info.getThreadState());
        }

        t1.join();
        t2.join();

        Executors.newSingleThreadExecutor();
    }
}