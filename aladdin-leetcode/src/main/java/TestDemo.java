import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class TestDemo {

    public static void main(String[] args) {

    }

    @Test
    public void testArrayAddressInMem() {

        int m = 5;
        int n = 4;
        Node[][] nodes = new Node[m][n];
        for (int i = 0; i < nodes.length; i++) {
            for (int i1 = 0; i1 < nodes[i].length; i1++) {
                nodes[i][i1] = new Node();
            }
        }

        Node tmp = null;
        for (int i = 0; i < nodes.length; i++) {
            for (int i1 = 0; i1 < nodes[i].length; i1++) {
                Node x = nodes[i][i1];

                System.out.printf("%s=", System.identityHashCode(x));

                if (tmp != null) {
                    System.out.printf("%d, ", System.identityHashCode(x) - System.identityHashCode(tmp));
                }
                tmp = x;
            }
            System.out.println();
        }

    }
}

class Node {
    private String nodeName;
}

