package linkedlist;

import org.junit.Test;

public class _206 {

    public ListNode reverseList(ListNode head) {
        ListNode reversed = head;
        ListNode next = head.next;
        reversed.next = null;
        while (next != null) {
            ListNode tmp = next.next;
            next.next = reversed;
            reversed = next;
            next = tmp;
        }
        return reversed;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5, 6};
        ListNode listNode = ListNode.buildList(a);
        ListNode listNode1 = reverseList(listNode);
        System.out.println(listNode);
        System.out.println(listNode1);
    }
}
