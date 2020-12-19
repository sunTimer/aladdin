import com.google.common.collect.Lists;
import linkedlist.ListNode;
import org.junit.Test;
import sun.misc.Unsafe;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDemo {



    public static void main(String[] args) {
        System.out.println(1 << 3);
    }

    @Test
    public void testArrayAddressInMem() {

        String join = String.join("-", Lists.newArrayList("1", "2"));
        System.out.println(join);
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

    @Test
    public void testReorderList2() {
        int[] a = {1, 2,3};
        ListNode head = ListNode.buildList(a);
        reorderList2(head);
        System.out.println(head);
    }


    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tail = slow.next;
        slow.next = null;

        ListNode reversedHead = reverse(tail);

        ListNode curr = head;
        while (reversedHead != null) {
            ListNode next = curr.next;
            curr.next = reversedHead;
            ListNode next1 = reversedHead.next;
            curr.next.next = next;
            curr = curr.next.next;
            reversedHead = next1;
        }
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head.next;
        ListNode reversed = head;
        reversed.next = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = reversed;
            reversed = curr;
            curr = next;
        }
        return reversed;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);

        ListNode curr = head;

        int carry = 0;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum += carry;
            if(l1 == null) {
                sum += l2.val;
                l2 = l2.next;
            }
            if(l2 == null) {
                sum += l1.val;
                l1 = l1.next;
            }

            carry = sum / 10;
            sum = sum % 10;

            curr.next = new ListNode(sum);
            curr = curr.next;
        }
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        return head.next;
    }

    @Test
    public void testRev() {
        int[] a = {1, 2, 3};
        System.out.println(reverse(ListNode.buildList(a)));
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        curr = head;
        int count = stack.size();
        int index = 0;
        while (index < count) {
            index += 2;
            ListNode next = curr.next;
            curr.next = stack.pop();
            curr.next.next = next;
            curr = next;
        }
        if ((count & 1) == 0) {
            curr.next = null;
        } else {
            curr.next.next = null;
        }
    }

    @Test
    public void test3() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListNode head = ListNode.buildList(a);
        reorderList(head);
        System.out.println(head);
    }

    @Test
    public void testSqrt(){
        System.out.println(sqrt(100, 0.00001));
    }

    double sqrt(double num, double d){
        double left = 1;
        double right = num;

        while (left < right) {
            double mid = (left + right) / 2;
            double tmp = mid * mid;

            if (tmp > num) {
                right = mid;
            } else {
                left = mid;
            }

            if (Math.abs(tmp - num) < d) {
                return mid;
            }
        }

        return -1;
    }
}

class Node {
    private String nodeName;
}

