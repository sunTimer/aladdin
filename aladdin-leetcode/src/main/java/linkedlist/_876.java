package linkedlist;

public class _876 {

    public ListNode middleNode(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        int i = 0;
        int mid = len >> 1;
        while (i < mid) {
            i++;
            head = head.next;
        }
        return head;
    }

    public ListNode middleNode2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
