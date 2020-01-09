package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _142 {

    public ListNode detectCycle(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();

        int i = 0;
        ListNode curr = head;
        while (curr != null) {
            Integer index = map.get(curr);
            if (index != null) {
                return curr;
            }
            map.put(curr, i++);
            curr = curr.next;
        }
        return null;
    }

    public ListNode detectCycle2(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        boolean hasCircle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                hasCircle = true;
                break;
            }
        }

        if (!hasCircle) {
            return null;
        }

        int circleLen = 1;

        slow = slow.next;
        while (slow != fast) {
            slow = slow.next;
            circleLen++;
        }

        slow = head;


        return head;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(detectCycle(listNode));
    }
}
