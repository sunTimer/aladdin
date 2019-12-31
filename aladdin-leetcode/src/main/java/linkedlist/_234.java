package linkedlist;

import org.junit.Assert;
import org.junit.Test;

public class _234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        int i = 0;
        ListNode reversed = head;
        ListNode next = head.next;
        head.next = null;
        int mid = len >> 1;
        while (i < mid - 1) {
            i++;
            ListNode tmp = next.next;
            next.next = reversed;
            reversed = next;
            next = tmp;
        }

        if (len % 2 == 1) {
            next = next.next;
        }

        while (next != null) {
            if (next.val != reversed.val) {
                return false;
            }
            next = next.next;
            reversed = reversed.next;
        }
        return true;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3};
        ListNode listNode = ListNode.buildList(a);
        Assert.assertFalse(isPalindrome(listNode));
    }
}
