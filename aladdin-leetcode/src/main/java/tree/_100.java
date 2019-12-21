package tree;

import org.junit.Test;

public class _100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            if (p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(3373752 % 16);
        System.out.println(3373752 & (16 - 1));
        System.out.println(8 << 2 + 16);
    }
}

