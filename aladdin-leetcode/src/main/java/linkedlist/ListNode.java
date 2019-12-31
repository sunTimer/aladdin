package linkedlist;

public class ListNode {

    public ListNode(int val) {
        this.val = val;
    }

    public int val;
    public ListNode next;

    public static ListNode buildList(int[] array) {
        ListNode listNode = new ListNode(0);
        ListNode curr = listNode;
        for (int i : array) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        return listNode.next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
