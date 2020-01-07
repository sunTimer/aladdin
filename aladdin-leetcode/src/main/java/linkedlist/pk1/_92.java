package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;

public class _92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        boolean flag = true;
        if (m == 1) {
            flag = false;
            m++;
            n++;
            ListNode newHead = new ListNode(0);
            newHead.next = head;
            head = newHead;
        }

        ListNode curr = head;
        int num = 1;
        while (num + 1 < m) {
            curr = curr.next;
            num++;
        }

        ListNode preTail = curr;
        ListNode reversedTail = curr.next;


        ListNode reversed = curr.next;
        ListNode next = curr.next.next;
        reversed.next = null;

        while (num + 1 < n) {
            ListNode tmp = next.next;
            next.next = reversed;
            reversed = next;
            next = tmp;
            num++;
        }

        ListNode postHead = next;

        preTail.next = reversed;
        reversedTail.next = postHead;

        return flag ? head : head.next;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(reverseBetween(listNode, 2, 3));
    }
}
