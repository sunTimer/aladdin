package linkedlist.pk3;

import linkedlist.ListNode;
import org.junit.Test;

public class _143 {

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.buildList(a);
        reorderList(listNode);
        System.out.println(listNode);
    }

    public void reorderList(ListNode head) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode slow = newHead;
        ListNode fast = newHead;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 节点个数为奇数：slow指向中间节点
        // 节点个数为偶数：slow指向后半第一个节点
        // 1 2 3 4   slow = 3
        // 1 2 3 4 5 slow = 3


        ListNode after = reverse(slow.next); // 4 3
        slow.next = null;
        ListNode before = head;

        ListNode curr = new ListNode(0);

        while (after != null || before != null) {
            if (before != null) {
                curr.next = before;
                before = before.next;
                curr = curr.next;
            }
            if (after != null) {
                curr.next = after;
                after = after.next;
                curr = curr.next;
            }
        }
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversed = head;
        head = head.next;
        reversed.next = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = reversed;
            reversed = head;
            head = next;
        }

        return reversed;
    }
}
