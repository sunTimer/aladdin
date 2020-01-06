package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;

public class _86 {

    public ListNode partition(ListNode head, int x) {
        ListNode big = new ListNode(0);
        ListNode small = new ListNode(0);

        ListNode bigHead = big;
        ListNode smallHead = small;


        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        small.next = bigHead.next;
        big.next = null;

        return smallHead.next;
    }

    @Test
    public void test() {
        int[] a = {1, 4, 3, 2, 5, 2};
        ListNode listNode = ListNode.buildList(a);
        ListNode partition = partition(listNode, 3);
        System.out.println(partition);
    }
}
