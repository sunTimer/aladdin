import org.junit.Assert;
import org.junit.Test;
import rule.milk.BinTreeEvaluator;
import rule.milk.OperatorType;
import rule.milk.TreeNode;

public class BinaryTreeExpressionGenTest {

    @Test
    public void test1() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        String[] words = {"a", "==", "1"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        Assert.assertEquals("==", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.value, "a");
        Assert.assertEquals(treeNode.right.value, "1");

        treeNode.printTree();
    }

    @Test
    public void test2() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        /**
         *  a == 1 && b == 2
         * <pre>
         *           &&
         *         /    |
         *        ==    ==
         *      /   |  /  |
         *     a    1  b   2
         *
         * </pre>
         */
        String[] words = {"a", "==", "1", "&&", "b", "==", "2"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        Assert.assertEquals("&&", treeNode.operatorType.getSymbol());
        Assert.assertEquals("==", treeNode.left.operatorType.getSymbol());
        Assert.assertEquals("==", treeNode.right.operatorType.getSymbol());
        Assert.assertEquals("a", treeNode.left.left.value);
        Assert.assertEquals("1", treeNode.left.right.value);

        Assert.assertEquals("b", treeNode.right.left.value);
        Assert.assertEquals("2", treeNode.right.right.value);

        treeNode.printTree();
    }

    @Test
    public void test3() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        String[] words = {"(", "a", "==", "1", ")"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        Assert.assertEquals("==", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.value, "a");
        Assert.assertEquals(treeNode.right.value, "1");

        treeNode.printTree();
    }

    @Test
    public void test4() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        String[] words = {"(", "(", "a", "==", "1", ")", ")"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        Assert.assertEquals("==", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.value, "a");
        Assert.assertEquals(treeNode.right.value, "1");

        treeNode.printTree();
    }


    @Test
    public void test5() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "drctn", "==", "22", ")"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        Assert.assertEquals("&&", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.operatorType, OperatorType.EQ);
        Assert.assertEquals("msgTp", treeNode.left.left.value);
        Assert.assertEquals("epcc.201.001.01", treeNode.left.right.value);

        Assert.assertEquals(treeNode.right.operatorType, OperatorType.EQ);
        Assert.assertEquals("drctn", treeNode.right.left.value);
        Assert.assertEquals("22", treeNode.right.right.value);

        treeNode.printTree();
    }

    @Test
    public void test6() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        /*
                                     ||
                                /           \
                         &&                          ==
                      /      \                  /          \
                  ==            !=        instOrgCode      alipay
                 /     \     /     \
              msgTp  epcc.  drctn  22
         */
        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "drctn", "!=", "22", ")", "||", "instOrgCode", "==", "alipay"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        Assert.assertEquals(OperatorType.OR, treeNode.operatorType);
        Assert.assertEquals(OperatorType.AND, treeNode.left.operatorType);
        Assert.assertEquals(OperatorType.EQ, treeNode.left.left.operatorType);
        Assert.assertEquals(OperatorType.NEQ, treeNode.left.right.operatorType);

        Assert.assertEquals(OperatorType.EQ, treeNode.right.operatorType);

        Assert.assertEquals("instOrgCode", treeNode.right.left.value);
        Assert.assertEquals("alipay", treeNode.right.right.value);

        treeNode.printTree();
    }

    @Test
    public void test7() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "drctn", "!=", "22", ")"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        Assert.assertEquals(OperatorType.AND, treeNode.operatorType);
        Assert.assertEquals(OperatorType.EQ, treeNode.left.operatorType);
        Assert.assertEquals("msgTp", treeNode.left.left.value);
        Assert.assertEquals("epcc.201.001.01", treeNode.left.right.value);

        Assert.assertEquals(OperatorType.NEQ, treeNode.right.operatorType);
        Assert.assertEquals("drctn", treeNode.right.left.value);
        Assert.assertEquals("22", treeNode.right.right.value);

        treeNode.printTree();
    }

    @Test
    public void test8() {
        BinTreeEvaluator binTreeEvaluator = new BinTreeEvaluator();
        // 最外层带括号的场景，还需要优化
//        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "(", "drctn", "!=", "22", "||", "acctTp", "!=", "01", ")", ")"};
        String[] words = {"msgTp", "==", "epcc.201.001.01", "&&", "(", "drctn", "!=", "22", "||", "(", "acctTp", "!=", "01", "||", "name", "!=", "shixu", ")", ")"};
        TreeNode treeNode = binTreeEvaluator.treeifyBin(words, 0, words.length);
        treeNode.printTree();

        Assert.assertEquals(OperatorType.AND, treeNode.operatorType);
        Assert.assertEquals(OperatorType.EQ, treeNode.left.operatorType);
        Assert.assertEquals("msgTp", treeNode.left.left.value);
        Assert.assertEquals("epcc.201.001.01", treeNode.left.right.value);

        Assert.assertEquals(OperatorType.NEQ, treeNode.right.operatorType);
        Assert.assertEquals("drctn", treeNode.right.left.value);
        Assert.assertEquals("22", treeNode.right.right.value);
    }
}

