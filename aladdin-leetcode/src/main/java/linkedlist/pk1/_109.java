package linkedlist.pk1;

import linkedlist.ListNode;
import org.junit.Test;
import tree.TreeNode;

public class _109 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        return buildTree(head, 1, len);
    }

    private TreeNode buildTree(ListNode head, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) >> 1;
        ListNode curr = head;
        for (int i = left; i < mid; i++) {
            curr = curr.next;
        }
        TreeNode root = new TreeNode(curr.val);
        root.left = buildTree(head, left, mid - 1);
        root.right = buildTree(curr.next, mid + 1, right);

        return root;
    }

    @Test
    public void test() {
        int[] a = {1, 2, 3, 4, 5};
        ListNode listNode = ListNode.buildList(a);
        System.out.println(sortedListToBST(listNode));
    }
}
