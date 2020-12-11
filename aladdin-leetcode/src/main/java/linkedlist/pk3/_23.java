package linkedlist.pk3;

import linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }

        ListNode newHead = new ListNode(0);
        ListNode curr = newHead;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }

        return newHead.next;
    }
}
