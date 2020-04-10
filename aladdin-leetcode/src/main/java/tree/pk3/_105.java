package tree.pk3;

import org.junit.Test;
import tree.TreeNode;


/**
 * <pre>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 通过次数56,511提交次数87,242
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 */
public class _105 {


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return buildTree(preorder, 0, preorder.length - 1,
                inorder, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                               int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preLeft]);

        int rootIndexInInorder = findRootIndexInInorder(root.val, inorder, inLeft, inRight);

        int leftLen = rootIndexInInorder - inLeft;
        root.left = buildTree(preorder, preLeft + 1, preLeft + leftLen,
                inorder, inLeft, rootIndexInInorder - 1);
        root.right = buildTree(preorder, preLeft + leftLen + 1, preRight,
                inorder, rootIndexInInorder + 1, inRight);
        return root;
    }

    private int findRootIndexInInorder(int rootVal, int[] inorder, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (rootVal == inorder[i]) {
                return i;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Test
    public void test() {
        int[] preorder = {1, 2, 4, 3};
        int[] inorder = {4, 2, 1, 3};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println(root);
    }
}
