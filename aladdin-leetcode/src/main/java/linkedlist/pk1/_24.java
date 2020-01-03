package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;

public class _24 {

    public ListNode swapPairs(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode first = head;
        ListNode second = head.next;

        ListNode newHead = new ListNode(0);
        ListNode pre = newHead;
        while (second != null) {
            first.next = second.next;
            second.next = first;
            pre.next = second;
            pre = first;

            if (first.next == null) {
                break;
            }
            first = first.next;
            second = first.next;
        }

        return newHead.next;
    }

    @Test
    public void test() {
        int[] a = {2, 5, 3};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(swapPairs(listNode));
    }
}
