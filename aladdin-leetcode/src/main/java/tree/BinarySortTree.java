package tree;

public class BinarySortTree {

    Node root;

    public Node search(Node root, int k) {
        if (root == null || root.val == k) {
            return root;
        }
        if (root.val > k) {
            return search(root.left, k);
        }
        return search(root.right, k);
    }

    public void insert(Node root, int k) {
        if (root == null) {
            this.root = new Node(k);
            return;
        }
        if (root.val >= k) {
            if (root.left == null) {
                root.left = new Node(k);
            } else {
                insert(root.left, k);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(k);
            } else {
                insert(root.right, k);
            }
        }
    }

    public Node minValue() {
        Node curr = root;
        if (curr == null) {
            return null;
        }
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }


    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();

        binarySortTree.insert(binarySortTree.root, 134);
        binarySortTree.insert(binarySortTree.root, 23);
        binarySortTree.insert(binarySortTree.root, 423);
        binarySortTree.insert(binarySortTree.root, 21);
        System.out.println(binarySortTree.root);

        System.out.println(binarySortTree.search(binarySortTree.root, 221));

        System.out.println(binarySortTree.minValue());
        binarySortTree.insert(binarySortTree.root, -1);
        System.out.println(binarySortTree.minValue());
    }
}

class Node {

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public Node(int val) {
        this.val = val;
    }

    int val;
    Node left;
    Node right;
}
