package offer.yuanfudao;

import org.junit.Test;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WidthTree {

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(5);
        System.out.println(width(root));
    }

    public int width(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int levelCount = 1;
        List<Integer> widthList = new ArrayList<>();
        widthList.add(levelCount);
        while (!queue.isEmpty()) {
            int tmp = 0;
            for (int i = 0; i < levelCount; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                    tmp++;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    tmp++;
                }
            }
            if (tmp != 0)
            widthList.add(tmp);
            levelCount = tmp;

        }
        System.out.println(widthList);
        return widthList.get(0);
    }

}
