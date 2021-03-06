package thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * <pre>
 *     假设有这么一个类：
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 */
public class _1116 {

    private int n;

    public _1116(int n) {
        this.n = n;
    }

    private int curr;

    Semaphore one = new Semaphore(1);
    Semaphore two = new Semaphore(0);
    Semaphore three = new Semaphore(0);


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            one.acquire();
            printNumber.accept(0);
            curr++;
            two.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (; curr < n; ) {
            two.acquire();
            printNumber.accept(curr);
            three.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (; curr < n; ) {
            three.acquire();
            printNumber.accept(curr);
            one.release();
        }
    }

    public static void main(String[] args) {
        _1116 z = new _1116(2);

        new Thread(() -> {
            try {
                z.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                z.even(value -> {
                    if (value % 2 == 1) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                z.odd(value -> {
                    if (value % 2 == 0) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
