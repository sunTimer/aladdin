package tree.pk5;

import org.junit.Test;
import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _1315 {

    @Test
    public void test() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(sumEvenGrandparent(root));
    }


    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int tmp = 0;

        if (root.val % 2 == 0) {
            if (root.left != null) {
                if (root.left.left != null) {
                    tmp += root.left.left.val;
                }
                if (root.left.right != null) {
                    tmp += root.left.right.val;
                }
            }

            if (root.right != null) {
                if (root.right.left != null) {
                    tmp += root.right.left.val;
                }
                if (root.right.right != null) {
                    tmp += root.right.right.val;
                }
            }
        }


        int left = sumEvenGrandparent(root.left);
        int right = sumEvenGrandparent(root.right);

        return left + right + tmp;
    }
}
