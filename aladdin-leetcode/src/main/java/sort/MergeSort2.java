package sort;

import linkedlist.ListNode;
import tree.TreeNode;

import java.util.*;

public class MergeSort2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);

        l1.next.next=  new ListNode(7);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(5);
        l2.next.next = new ListNode(9);

        ListNode merge = merge(l1, l2);

        System.out.println(merge);
    }

    static ListNode head = new ListNode(0);

    static ListNode curr = head;

    public static ListNode merge(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return curr.next;
        }

        if (l1 == null) {
            curr.next = l2;
            return head.next;
        }

        if (l2 == null) {
            curr.next = l1;
            return head.next;
        }

        if (l1.val < l2.val) {
            curr.next = l1;
            l1 = l1.next;
        } else {
            curr.next = l2;
            l2 = l2.next;
        }
        curr = curr.next;
        return merge(l1, l2);
    }



}
