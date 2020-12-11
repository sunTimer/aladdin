package linkedlist.pk2;

import linkedlist.ListNode;

public class _237 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
