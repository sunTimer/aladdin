package tree.pk3;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        while (!queue.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            int childSize = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.pop();
                tmp.add(curr.val);
                if (curr.left != null) {
                    queue.add(curr.left);
                    childSize++;
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                    childSize++;
                }
            }
            size = childSize;
            ret.add(tmp);
        }
        return ret;
    }
}
