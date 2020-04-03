package expression;

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

        TreeEvaluator treeEvaluator = new TreeEvaluator();
        String seq = "(msgTp==epcc.201.001.01&&drctn==22)";


        int n = 1000;

        List<TreeExpression> treeExpressions = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            treeExpressions.add(treeEvaluator.compile(seq));
        }

        for (int i = 0; i < 10000; i++) {
            long start = System.nanoTime();
            for (TreeExpression treeExpression : treeExpressions) {
                treeExpression.execute(map, treeExpression.root);
            }
            System.out.println("cost:" + (System.nanoTime() - start) / 1000.0 / 1000.0);
        }
    }
}
