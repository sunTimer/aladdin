package linkedlist.pk3;

import linkedlist.ListNode;
import org.junit.Test;

public class _92 {

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5, 6};
        ListNode listNode = ListNode.buildList(a);
        ListNode listNode1 = reverseBetween(listNode, 1, 2);
        System.out.println(listNode1);
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        if (m == n) {
            return head;
        }

        ListNode curr = head;
        ListNode startPre = null;
        for (int i = 1; i <= n; i++) {
            if (i == m - 1) {
                startPre = curr;
            }
            curr = curr.next;
        }

        ListNode endNext = curr;


        ListNode start;
        if (m == 1) {
            start = head;
        } else {
            start = startPre.next;
        }
        ListNode reversed = reverse(start, endNext);

        start.next = endNext;

        if (m == 1) {
            return reversed;
        }
        startPre.next = reversed;
        return head;
    }

    public ListNode reverse(ListNode start, ListNode end) {
        ListNode reversed = null;

        while (start != end) {
            ListNode next = start.next;
            start.next = reversed;
            reversed = start;
            start = next;
        }

        return reversed;
    }
}
