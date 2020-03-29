import org.junit.Assert;
import org.junit.Test;
import rule.milk.BinaryTreeExpression;
import rule.milk.BinTreeExpressionGen;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeExpressionTest {

    BinTreeExpressionGen binTreeExpressionGen = new BinTreeExpressionGen();


    @Test
    public void test() {
        String[] words = {
                "msgTp", "==", "epcc.201.001.01",
                "&&",
                "drctn", "==", "22",
                "&&",
                "instOrgCode", "==", "alipay",
                "||",
                "bankOrgCode", "==", "ICBC",
                "#"
        };
        BinaryTreeExpression expression = binTreeExpressionGen.compile(words);

        Map<String, String> param = new HashMap<>();
        param.put("msgTp", "epcc.201.001.01");
        param.put("drctn", "22");
        param.put("instOrgCode", "alipay");
        param.put("bankOrgCode", "ICBC");
        boolean ret = expression.execute(param);
        Assert.assertTrue(ret);
    }

    @Test
    public void test1() {
        String[] words = {
                "msgTp", "==", "epcc.201.001.01",
                "&&",
                "drctn", "==", "22",
                "&&",
                "(",
                "instOrgCode", "==", "alipay",
                "||",
                "instOrgCode", "==", "tenPay",
                ")",
                "#"
        };
        BinaryTreeExpression expression = binTreeExpressionGen.compile(words);

        Map<String, String> param = new HashMap<>();
        param.put("msgTp", "epcc.201.001.01");
        param.put("drctn", "22");
        param.put("instOrgCode", "alipay");
        param.put("bankOrgCode", "ICBC");
        boolean ret = expression.execute(param);
        Assert.assertTrue(ret);

        param.put("instOrgCode", "tenPay");
        ret = expression.execute(param);
        Assert.assertTrue(ret);

//        param.put("instOrgCode", "JDPay");
        param.put("msgTp", "epcc.211.001.01");
        ret = expression.execute(param);
        System.out.println(expression.root);
        Assert.assertFalse(ret);


        Assert.assertTrue(true || false && true);
        Assert.assertTrue(false && true || true);

    }
}
