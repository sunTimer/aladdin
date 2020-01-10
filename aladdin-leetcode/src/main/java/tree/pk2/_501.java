package tree.pk2;

import org.junit.Test;
import tree.TreeNode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class _501 {

    public int[] findMode2(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> inOrderList = new LinkedList<>();
        inOrder(root, inOrderList);
        Map<Integer, Integer> ret = new HashMap<>();
        for (Integer i : inOrderList) {
            if (ret.containsKey(i)) {
                ret.put(i, ret.get(i) + 1);
            } else {
                ret.put(i, 1);
            }
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(ret.entrySet());

        entryList.sort((o1, o2) -> o2.getValue() - o1.getValue());

        List<Integer> list = new ArrayList<>();
        int size = 0;
        for (Map.Entry<Integer, Integer> entry : entryList) {
            if (entry.getValue() >= size) {
                list.add(entry.getKey());
                size = entry.getValue();
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void inOrder(TreeNode root, List<Integer> inOrderList) {
        if (root == null) {
            return;
        }
        inOrder(root.left, inOrderList);
        inOrderList.add(root.val);
        inOrder(root.right, inOrderList);
    }

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Set<Integer> ret = new LinkedHashSet<>();
        ret.add(root.val);
        AtomicInteger currMax = new AtomicInteger(1);

        find(root, root.left, currMax, 1, ret);
        find(root, root.right, currMax, 1, ret);

        int[] retA = new int[ret.size()];
        int index = 0;
        for (Integer i : ret) {
            retA[index++] = i;
        }
        return retA;
    }

    private void find(TreeNode parentNode, TreeNode currNode, AtomicInteger currMax, int currCount, Set<Integer> ret) {
        if (currNode == null) {
            return;
        }

        if (currNode.val != parentNode.val) {
            find(currNode, currNode.left, currMax, 1, ret);
            find(currNode, currNode.right, currMax, 1, ret);
        } else {
            currCount++;
            if (currCount > currMax.get()) {
                currMax.set(currCount);
                ret.clear();
                ret.add(currNode.val);
            } else if (currCount == currMax.get()) {
                ret.add(currNode.val);
            }
            find(currNode, currNode.left, currMax, currCount, ret);
            find(currNode, currNode.right, currMax, currCount, ret);
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);

        root.right = new TreeNode(2);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(2);

        int[] ret = findMode2(root);
        for (int i : ret) {
            System.out.println(i);
        }
    }
}
