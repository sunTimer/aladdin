import org.junit.Assert;
import org.junit.Test;
import rule.milk.OperatorType;
import rule.milk.ExpressionTreeGen;
import rule.milk.TreeNode;

public class ExpressionTreeGenTest {

    @Test
    public void test1() {
        ExpressionTreeGen expressionTreeGen = new ExpressionTreeGen();
        String[] words = {"a", "==", "1", "#"};
        TreeNode treeNode = expressionTreeGen.buildTree(words, 0, words.length);
        Assert.assertEquals("==", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.value, "a");
        Assert.assertEquals(treeNode.right.value, "1");
    }

    @Test
    public void test2() {
        ExpressionTreeGen expressionTreeGen = new ExpressionTreeGen();
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
        String[] words = {"a", "==", "1", "&&", "b", "==", "2", "#"};
        TreeNode treeNode = expressionTreeGen.buildTree(words, 0, words.length);
        Assert.assertEquals("&&", treeNode.operatorType.getSymbol());
        Assert.assertEquals("==", treeNode.left.operatorType.getSymbol());
        Assert.assertEquals("==", treeNode.right.operatorType.getSymbol());
        Assert.assertEquals("a", treeNode.left.left.value);
        Assert.assertEquals("1", treeNode.left.right.value);

        Assert.assertEquals("b", treeNode.right.left.value);
        Assert.assertEquals("2", treeNode.right.right.value);
    }

    @Test
    public void test3() {
        ExpressionTreeGen expressionTreeGen = new ExpressionTreeGen();
        String[] words = {"(", "a", "==", "1", ")", "#"};
        TreeNode treeNode = expressionTreeGen.buildTree(words, 0, words.length);
        Assert.assertEquals("==", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.value, "a");
        Assert.assertEquals(treeNode.right.value, "1");
    }

    @Test
    public void test4() {
        ExpressionTreeGen expressionTreeGen = new ExpressionTreeGen();
        String[] words = {"(", "(", "a", "==", "1", ")", ")", "#"};
        TreeNode treeNode = expressionTreeGen.buildTree(words, 0, words.length);
        Assert.assertEquals("==", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.value, "a");
        Assert.assertEquals(treeNode.right.value, "1");
    }


    @Test
    public void test5() {
        ExpressionTreeGen expressionTreeGen = new ExpressionTreeGen();
        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "drctn", "==", "22", ")", "#"};
        TreeNode treeNode = expressionTreeGen.buildTree(words, 0, words.length);
        Assert.assertEquals("&&", treeNode.operatorType.getSymbol());
        Assert.assertEquals(treeNode.left.operatorType, OperatorType.EQ);
        Assert.assertEquals("msgTp", treeNode.left.left.value);
        Assert.assertEquals("epcc.201.001.01", treeNode.left.right.value);

        Assert.assertEquals(treeNode.right.operatorType, OperatorType.EQ);
        Assert.assertEquals("drctn", treeNode.right.left.value);
        Assert.assertEquals("22", treeNode.right.right.value);
    }

    @Test
    public void test6() {
        ExpressionTreeGen expressionTreeGen = new ExpressionTreeGen();
        String[] words = {"(", "msgTp", "==", "epcc.201.001.01", "&&", "drctn", "!=", "22", ")", "||", "instOrgCode", "==", "alipay", "#"};
        TreeNode treeNode = expressionTreeGen.buildTree(words, 0, words.length);
        Assert.assertEquals(OperatorType.OR, treeNode.operatorType);
        Assert.assertEquals(OperatorType.AND, treeNode.left.operatorType);
        Assert.assertEquals(OperatorType.EQ, treeNode.left.left.operatorType);
        Assert.assertEquals(OperatorType.NEQ, treeNode.left.right.operatorType);

        Assert.assertEquals(OperatorType.EQ, treeNode.right.operatorType);

        Assert.assertEquals("instOrgCode", treeNode.right.left.value);
        Assert.assertEquals("alipay", treeNode.right.right.value);
    }
}
