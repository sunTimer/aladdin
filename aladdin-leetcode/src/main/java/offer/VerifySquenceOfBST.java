package offer;

import org.junit.Assert;
import org.junit.Test;

public class VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int left, int right) {
        // 1. 只有一个节点，是bst
        if (left >= right) {
            return true;
        }

        // 2. 后序遍历，故最后一个节点是根节点
        int rootVal = sequence[right];

        // 3. 寻找左子树的根节点位置
        int leftChildRootIndex = left - 1;
        for (int i = right - 1; i >= left; i--) {
            if (sequence[i] < rootVal) {
                leftChildRootIndex = i;
                break;
            }
        }

        // 4. 说明没有左子树，只有右子树，则只需校验右子树
        if (leftChildRootIndex == left - 1) {
            return verify(sequence, left, right - 1);
        }

        // 5. 先判断左子树中是否所有元素都小于根节点
        for (int i = left; i <= leftChildRootIndex; i++) {
            if (sequence[i] > rootVal) {
                return false;
            }
        }

        // 6. 递归校验左右子树
        return verify(sequence, left, leftChildRootIndex)
                && verify(sequence, leftChildRootIndex + 1, right - 1);
    }

    @Test
    public void test() {
        int[] bst = {12, 20, 25, 9};
        boolean b = VerifySquenceOfBST(bst);
        Assert.assertTrue(b);
    }
}
