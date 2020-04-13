package thread;

public class _1115_Foobar2 {


    private int n;

    private final Object lock = new Object();
    private boolean flag = true;

    public _1115_Foobar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (!flag) {
                    lock.wait();
                }
                printFoo.run();
                flag = false;
                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                while (flag) {
                    lock.wait();
                }
                printBar.run();
                flag = true;
                lock.notify();
            }
        }
    }


    public static void main(String[] args) {
        _1115_Foobar2 foobar = new _1115_Foobar2(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foobar.foo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("foo");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foobar.bar(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("bar");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
