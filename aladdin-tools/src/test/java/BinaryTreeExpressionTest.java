import org.junit.Assert;
import org.junit.Test;
import rule.milk.BinTreeEvaluator;
import rule.milk.BinaryTreeExpression;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeExpressionTest {

    BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();

    @Test
    public void test() {
        String[] words = {
                "msgTp", "==", "epcc.201.001.01",
                "&&",
                "drctn", "==", "22",
                "&&",
                "instOrgCode", "==", "alipay",
                "||",
                "bankOrgCode", "==", "ICBC"
        };
        BinaryTreeExpression expression = binTreeEvaluator.compile(words);

        Map<String, String> param = new HashMap<>();
        param.put("msgTp", "epcc.201.001.01");
        param.put("drctn", "22");
        param.put("instOrgCode", "alipay");
        param.put("bankOrgCode", "ICBC");
        boolean ret = expression.execute(param);
        Assert.assertTrue(ret);

        expression.root.printTree();
    }

    @Test
    public void test1() {
        String[] words = {
                "msgTp", "==", "epcc.201.001.01"
                , "&&"
                , "drctn", "==", "22"
                , "&&"
                , "("
                , "instOrgCode", "==", "alipay"
                , "||"
                , "instOrgCode", "==", "tenPay"
                , "||"
                , "instOrgCode", "==", "JDPay"
                , ")"
        };
        BinaryTreeExpression expression = binTreeEvaluator.compile(words);

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

        param.put("instOrgCode", "JDPay");
        ret = expression.execute(param);
        Assert.assertTrue(ret);

        param.put("msgTp", "epcc.211.001.01");
        ret = expression.execute(param);
        Assert.assertFalse(ret);

        expression.root.printTree();
    }

    @Test
    public void test2() {
        String[] words = {
                "("
                , "msgTp", "==", "epcc.201.001.01"
                , "&&"
                , "drctn", "==", "22"
                , "&&"
                , "acctTp", "!=", "01"
                , ")"
                , "&&"
                , "("
                , "instOrgCode", "==", "alipay"
                , "||"
                , "instOrgCode", "==", "tenPay"
                , "||"
                , "instOrgCode", "==", "JDPay"
                , "||"
                , "instOrgCode", "==", "NetEasyPay"
                , ")"
        };
        BinaryTreeExpression expression = binTreeEvaluator.compile(words);

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

        param.put("instOrgCode", "JDPay");
        ret = expression.execute(param);
        Assert.assertTrue(ret);

        param.put("msgTp", "epcc.211.001.01");
        ret = expression.execute(param);
        Assert.assertFalse(ret);

        expression.root.printTree();
    }


    @Test
    public void test3() {
        String[] words = {
                "("
                , "msgTp", "==", "epcc.201.001.01"
                , "&&"
                , "("
                , "drctn", "==", "22"
                , "&&"
                , "acctTp", "!=", "01"
                , ")"
                , ")"
                , "&&"
                , "("
                , "instOrgCode", "==", "alipay"
                , "||"
                , "instOrgCode", "==", "tenPay"
                , "||"
                , "instOrgCode", "==", "JDPay"
                , "||"
                , "instOrgCode", "==", "NetEasyPay"
                , ")"
        };
        BinaryTreeExpression expression = binTreeEvaluator.compile(words);

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

        param.put("instOrgCode", "JDPay");
        ret = expression.execute(param);
        Assert.assertTrue(ret);

        param.put("msgTp", "epcc.211.001.01");
        ret = expression.execute(param);
        Assert.assertFalse(ret);

        expression.root.printTree();
    }
}
