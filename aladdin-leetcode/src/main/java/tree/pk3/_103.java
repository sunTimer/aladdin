package tree.pk3;

import org.junit.Test;
import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树蛇形遍历
 */
public class _103 {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> subRet = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                int level = ret.size();
                if ((level & 1) == 0) {
                    // 偶数层
                    subRet.add(curr.val);
                } else {
                    // 奇数层
                    subRet.addFirst(curr.val);
                }
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            ret.add(subRet);
        }
        return ret;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(13);
        root.right.left = new TreeNode(15);
        root.right.left.left = new TreeNode(25);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(21);

        List<List<Integer>> lists = zigzagLevelOrder(root);
        System.out.println(lists);
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        System.out.println(linkedList);
        linkedList.push(3);
        System.out.println(linkedList);
        linkedList.addFirst(4);
        System.out.println(linkedList);
        linkedList.addLast(5);
        System.out.println(linkedList);

        System.out.println(linkedList.poll());
        System.out.println(linkedList);
        System.out.println(linkedList.pollFirst());
        System.out.println(linkedList);
        System.out.println(linkedList.pollLast());
        System.out.println(linkedList);
    }
}
