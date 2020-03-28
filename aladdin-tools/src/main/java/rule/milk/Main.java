package rule.milk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("instOrgCode", "alipay");
        map.put("msgTp", "epcc.201.001.01");
        map.put("drctn", "22");

        ExpressionTreeGen expressionTreeGen = new ExpressionTreeGen();
//        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "drctn", "==", "22", ")", "||", "instOrgCode", "!=", "alipay", "#"};
        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "drctn", "==", "22", ")", "#"};
        //String[] words = {"msgTp", "==", "epcc.201.001.01", "#"};


        int n = 50000;

        List<ExpressionTree> expressionTrees = new ArrayList<>(n);


        for (int i = 0; i < n; i++) {
            TreeNode treeNode = expressionTreeGen.buildTree(words, 0, words.length);
            expressionTrees.add(new ExpressionTree(treeNode));
        }

        for (int i = 0; i < 10000; i++) {

            long start = System.nanoTime();
            for (ExpressionTree expressionTree : expressionTrees) {
                expressionTree.execute(map, expressionTree.root);
            }
            System.out.println("cost:" + (System.nanoTime() - start) / 1000.0 / 1000.0);
        }


    }
}
