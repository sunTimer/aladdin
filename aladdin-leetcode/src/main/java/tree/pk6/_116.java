package tree.pk6;

import org.junit.Test;

public class _116 {

    @Test
    public void test(){
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        Node root = connect(node);
        System.out.println(root);
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        connect(root.left, root.right);
        return root;
    }

    public void connect(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        node1.next = node2;

        connect(node1.left, node1.right);
        connect(node1.right, node2.left);
        connect(node2.left, node2.right);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                ", next=" + next +
                '}';
    }
};