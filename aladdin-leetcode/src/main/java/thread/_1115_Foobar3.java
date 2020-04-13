package thread;

import java.util.concurrent.Semaphore;

public class _1115_Foobar3 {


    private int n;


    public _1115_Foobar3(int n) {
        this.n = n;
    }

    Semaphore one = new Semaphore(1);
    Semaphore two = new Semaphore(0);


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            one.acquire();
            printFoo.run();
            two.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            two.acquire();
            printBar.run();
            one.release();
        }
    }


    public static void main(String[] args) {
        _1115_Foobar3 foobar = new _1115_Foobar3(4);
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
