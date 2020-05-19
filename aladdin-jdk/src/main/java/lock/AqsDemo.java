package lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AqsDemo {

    class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            acquire(arg);
            return super.tryAcquire(arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }
    }
}
