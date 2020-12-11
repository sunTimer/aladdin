package linkedlist.pk3;

import linkedlist.ListNode;
import org.junit.Test;

public class _25 {

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.buildList(a);
        ListNode listNode1 = reverseKGroup(listNode, 5);
        System.out.println(listNode1);
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        if (k == 1) {
            return head;
        }
        ListNode result = new ListNode(0);
        ListNode tail = result;

        ListNode reversedStart = null;
        ListNode curr = head;
        int index = 1;

        while (curr != null) {
            if (index % k == 1) {
                reversedStart = curr;
            }

            ListNode next = curr.next;
            if (index % k == 0) {
                curr.next = null;
                tail.next = reverse(reversedStart);
                reversedStart.next = next;
                tail = reversedStart;
            }

            index++;
            curr = next;
        }
        return result.next;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversed = head;
        ListNode curr = head.next;
        reversed.next = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = reversed;
            reversed = curr;
            curr = next;
        }
        return reversed;
    }
}
