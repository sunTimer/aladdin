package lock;

public class SyncDemo {

    public synchronized int sync1(int c) {
        int a = 1;
        int b = 2;
        c = a + b;
        return c;
    }

    public int sync2(int c) {
        synchronized (this) {
            int a = 1;
            int b = 2;
            c = a + b;
            return c;
        }
    }
}
