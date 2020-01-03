package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;

public class _19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        if (fast == null) {
            return head.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    @Test
    public void test(){
        int[] a = {1,2};
        ListNode listNode = ListNode.buildList(a);
        ListNode listNode1 = removeNthFromEnd(listNode, 1);
        System.out.println(listNode1);
    }
}
