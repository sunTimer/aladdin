package linkedlist.pk2;

import linkedlist.ListNode;
import org.junit.Test;

public class _148 {
    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    public ListNode mergeSort(ListNode head) {


        if (head.next == null) {
            return head;
        }
        ListNode p = head, q = head, pre = null;
        while (q != null && q.next != null) {
            pre = p;
            p = p.next;
            q = q.next.next;
        }
        pre.next = null;
        ListNode l = mergeSort(head);
        ListNode r = mergeSort(p);
        return merge(l, r);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);

        ListNode curr = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                curr.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            curr = curr.next;
        }
        while (l1 != null) {
            curr.next = new ListNode(l1.val);
            l1 = l1.next;
            curr = curr.next;
        }

        while (l2 != null) {
            curr.next = new ListNode(l2.val);
            l2 = l2.next;
            curr = curr.next;
        }
        return head.next;
    }

    @Test
    public void test() {
        int[] a = {4, 1, 2, 3};
        System.out.println(sortList(ListNode.buildList(a)));
    }
}
