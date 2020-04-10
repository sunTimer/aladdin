package tree.pk3;

import tree.TreeNode;

import java.util.List;

public class PostTravelTree {

    public List<Integer> postorderTraversal(TreeNode root) {


        return null;
    }

    public static void main(String[] args) {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(sum);
    }
}
