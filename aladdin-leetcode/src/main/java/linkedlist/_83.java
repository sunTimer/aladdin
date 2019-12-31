package linkedlist;

import org.junit.Test;

public class _83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast.val != slow.val) {
                fast = fast.next;
                slow = slow.next;
            } else if (fast.val == slow.val) {
                slow.next = fast.next;
                fast = slow.next;
            }
        }
        return head;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 2, 2, 3, 4};
        ListNode head = ListNode.buildList(a);
        ListNode listNode = deleteDuplicates(head);
        System.out.println(listNode);
    }
}
