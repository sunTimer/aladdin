package linkedlist.pk3;

import linkedlist.ListNode;
import org.junit.Test;

public class _234 {

    @Test
    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode reverse;
        if (fast == null) {
            reverse = reverse(slow);
        } else {
            reverse = reverse(slow.next);
        }
        return compare(head, reverse);
    }

    public boolean compare(ListNode l1, ListNode l2){
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val){
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversed = head;
        ListNode curr = head.next;
        reversed.next = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = reversed;
            reversed = curr;
            curr = next;
        }

        return reversed;

    }
}
