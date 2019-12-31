package linkedlist;

public class _203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode tmp = new ListNode(0);
        tmp.next = head;

        ListNode slow = tmp;
        ListNode fast =tmp.next;

        while (fast != null) {
            if (fast.val == val) {
                slow.next = fast.next;
                fast = fast.next;
            } else if (fast.val != val) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return tmp.next;
    }
}
