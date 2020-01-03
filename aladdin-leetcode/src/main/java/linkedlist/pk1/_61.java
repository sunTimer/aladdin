package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;

public class _61 {

    public ListNode rotateRight(ListNode head, int k) {

        if (k == 0 || head == null) {
            return head;
        }

        ListNode fast = head;
        while (k > 0) {
            k--;
            fast = fast.next;
            if (fast == null) {
                fast = head;
            }
        }

        if (fast == head) {
            return head;
        }

        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(rotateRight(listNode, 4));
    }
}
