package sort;

import org.junit.Test;

public class MergeSort {

    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddle(head);
        Node middleNext = middle.next;
        middle.next = null;

        Node l = mergeSort(head);
        Node r = mergeSort(middleNext);

        return merge(l, r);
    }

    private Node merge(Node l, Node r) {
        Node head = new Node(0);
        Node sorted = head;
        while (l != null || r != null) {
            if (l == null) {
                sorted.next = r;
                break;
            }
            if (r == null) {
                sorted.next = l;
                break;
            }
            if (l.value > r.value) {
                sorted.next = r;
                r = r.next;
            } else {
                sorted.next = l;
                l = l.next;
            }
            sorted = sorted.next;
        }
        return head.next;
    }

    private Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void test() {
        Node head = new Node(32);
        head.next = new Node(23);
        mergeSort(head);
        System.out.println(head);
    }
}

class Node {
    public Node(int value) {
        this.value = value;
    }

    Node next;
    int value;

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", value=" + value +
                '}';
    }
}