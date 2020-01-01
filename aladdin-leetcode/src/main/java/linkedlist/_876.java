package linkedlist;

import org.junit.Assert;
import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;

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

    public static void main(String[] args) {
        int i = 0;
        double base = 20;
        while (base < 30) {
            base += base * 0.05;
            i++;
        }
        System.out.println(i);
    }

    public boolean find(int target, int[][] array) {
        int m = 0;
        int n = array[0].length - 1;

        while (m < array.length && n >= 0) {
            if (target < array[m][n]) {
                n--;
                continue;
            }
            if (target > array[m][n]) {
                m++;
                continue;
            }
            return true;
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {

        return null;
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>();
        }

        ListNode reversed = listNode;
        ListNode curr = listNode.next;
        listNode.next = null;

        int size = 1;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = reversed;
            reversed = curr;
            curr = tmp;
            size++;
        }

        ArrayList<Integer> list = new ArrayList<>(size);

        while (reversed != null) {
            list.add(reversed.val);
            reversed = reversed.next;
        }
        return list;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        int preLeft = 0;
        int preRight = pre.length - 1;

        int inLeft = 0;
        int inRight = in.length - 1;

        return build(pre, preLeft, preRight, in, inLeft, inRight);
    }

    public TreeNode build(int[] pre, int preLeft, int preRight,
                          int[] in, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        int rootIndexInPre = preLeft;
        int rootIndexInIn = findIn(pre[rootIndexInPre], in);

        TreeNode root = new TreeNode(pre[rootIndexInPre]);
        TreeNode left = build(pre
                , rootIndexInPre + 1
                , rootIndexInPre + rootIndexInIn - inLeft
                , in
                , inLeft
                , rootIndexInIn - 1);
        TreeNode right = build(pre
                , rootIndexInPre + rootIndexInIn - inLeft + 1
                , preRight
                , in
                , rootIndexInIn + 1
                , inRight);
        root.left = left;
        root.right = right;
        return root;
    }

    public int findIn(int target, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Assert.assertTrue(find(9, array));

        int[] a = {1, 2, 3};
        ArrayList<Integer> integers = printListFromTailToHead(ListNode.buildList(a));
        System.out.println(integers);

        int[] pre = {1, 2, 3, 4, 5, 6, 7};
        int[] in = {3, 2, 4, 1, 6, 5, 7};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        System.out.println(treeNode);
    }
}
