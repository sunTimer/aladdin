package linkedlist.pk2;

import linkedlist.ListNode;
import org.junit.Test;

public class _171 {

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5, 6};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(oddEvenList(listNode));
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode jiHead = head;
        ListNode ouHead = head.next;
        ListNode curr = head.next.next;

        jiHead.next = null;
        ouHead.next = null;

        ListNode jiTail = jiHead;
        ListNode ouTail = ouHead;

        boolean flag = true;
        while (curr != null) {
            ListNode next = curr.next;
            if (flag) {
                jiTail.next = curr;
                jiTail = jiTail.next;
                jiTail.next = null;
            } else {
                ouTail.next = curr;
                ouTail = ouTail.next;
                ouTail.next = null;
            }
            flag = !flag;
            curr = next;
        }

        jiTail.next = ouHead;
        return jiHead;
    }
}
