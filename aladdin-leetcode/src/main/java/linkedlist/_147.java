package linkedlist;

import org.junit.Test;

public class _147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sortedHead = head;

        ListNode noSortedHead = head.next;

        sortedHead.next = null;

        while (noSortedHead != null) {
            ListNode next = noSortedHead.next;
            if (noSortedHead.val <= sortedHead.val) {
                noSortedHead.next = sortedHead;
                sortedHead = noSortedHead;
            } else {
                ListNode tail = sortedHead;
                while (tail.next != null && tail.next.val < noSortedHead.val) {
                    tail = tail.next;
                }
                noSortedHead.next = tail.next;
                tail.next = noSortedHead;
            }
            noSortedHead = next;
        }
        return sortedHead;
    }

    @Test
    public void test(){
        int[] a = {24,22,341,231,54,672,3444,123,4124,12};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(insertionSortList(listNode));
    }
}
