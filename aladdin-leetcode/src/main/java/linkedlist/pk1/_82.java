package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;

public class _82 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode newHead = new ListNode(head.val - 1);
        newHead.next = head;
        //       pre   base              next
        // 1->   2->   3->   3->   3->   4->   5->  6
        ListNode pre = newHead;
        ListNode base = head;
        ListNode next = head.next;
        boolean flag = false;
        while (next != null) {
            while (next.val == base.val) {
                next = next.next;
                flag = true;
                if (next == null) {
                    pre.next = null;
                    return newHead.next;
                }
            }
            if (flag) {
                pre.next = next;
                base = next;
                next = next.next;
                flag = false;
            } else {
                pre = pre.next;
                base = base.next;
                next = next.next;
            }
        }
        return newHead.next;
    }

    @Test
    public void test() {
        int[] a = {1, 1, 3};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(deleteDuplicates(listNode));
    }
}
