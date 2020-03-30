import expression.PostfixExpression;
import expression.Token;
import org.junit.Assert;
import org.junit.Test;
import rule.milk.BinaryTreeExpression;
import rule.milk.OperatorType;
import rule.milk.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class PostfixExpressionTest {

    @Test
    public void test() {
        PostfixExpression postfixExpression = new PostfixExpression();
        Token[] tokens = {
                new Token(OperatorType.NUM, "a")
                , new Token(OperatorType.NUM, "1")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "b")
                , new Token(OperatorType.NUM, "2")
                , new Token(OperatorType.NEQ, "!=")
                , new Token(OperatorType.AND, "&&")
        };
        TreeNode root = postfixExpression.treeifyBin(tokens);
        root.printTree();
    }


    @Test
    public void test2() {
        PostfixExpression postfixExpression = new PostfixExpression();

        // 4.99 * 1.06 + 5.99 + 6.99 * 1.06
        // 4.99 1.06 * 5.99 + 6.99 1.06 * +
        Token[] tokens = {
                new Token(OperatorType.NUM, "4.99")
                , new Token(OperatorType.NUM, "1.06")
                , new Token(OperatorType.MULT, "*")
                , new Token(OperatorType.NUM, "5.99")
                , new Token(OperatorType.ADD, "+")
                , new Token(OperatorType.NUM, "6.99")
                , new Token(OperatorType.NUM, "1.06")
                , new Token(OperatorType.MULT, "*")
                , new Token(OperatorType.ADD, "+")
        };
        double ret = postfixExpression.execute(tokens);
        System.out.println(ret);
    }

    @Test
    public void test3() {
        PostfixExpression postfixExpression = new PostfixExpression();
        // 中缀：a == 1 && (b == 2 || b == 3 || b == 4)
        // 后缀：a 1 == b 2 == b 3 == || b 4 == || &&
        Token[] tokens = {
                new Token(OperatorType.NUM, "a")
                , new Token(OperatorType.NUM, "1")
                , new Token(OperatorType.EQ, "==")

                , new Token(OperatorType.NUM, "b")
                , new Token(OperatorType.NUM, "2")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "b")
                , new Token(OperatorType.NUM, "3")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.OR, "||")

                , new Token(OperatorType.NUM, "b")
                , new Token(OperatorType.NUM, "4")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.OR, "||")

                , new Token(OperatorType.AND, "&&")
        };
        TreeNode root = postfixExpression.treeifyBin(tokens);
        root.printTree();
    }

    @Test
    public void testInfixToPostfix() {
        // 4.99 * 1.06 + 5.99 + 6.99 * 1.06
        // 4.99 1.06 * 5.99 + 6.99 1.06 * +
        PostfixExpression postfixExpression = new PostfixExpression();

        Token[] tokens = {
                new Token(OperatorType.NUM, "4.99")
                , new Token(OperatorType.MULT, "*")
                , new Token(OperatorType.NUM, "1.06")
                , new Token(OperatorType.ADD, "+")
                , new Token(OperatorType.NUM, "5.99")
                , new Token(OperatorType.ADD, "+")
                , new Token(OperatorType.NUM, "6.99")
                , new Token(OperatorType.MULT, "*")
                , new Token(OperatorType.NUM, "1.06")
        };
        Token[] postfix = postfixExpression.infixToPostfix(tokens);
        for (Token token : postfix) {
            System.out.println(token);
        }
    }


    @Test
    public void testInfixToPostfix2() {
        // a == 1 && b == 2 || c == 3
        // a 1 == b 2 == && c 3 == ||
        PostfixExpression postfixExpression = new PostfixExpression();
        // a 1 == b 2 ==
        // &&

        Token[] tokens = {
                new Token(OperatorType.NUM, "a")
                , new Token(OperatorType.NEQ, "!=")
                , new Token(OperatorType.NUM, "1")
                , new Token(OperatorType.AND, "&&")
                , new Token(OperatorType.NUM, "b")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "2")
                , new Token(OperatorType.OR, "||")
                , new Token(OperatorType.NUM, "c")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "3")
                , new Token(OperatorType.AND, "&&")
                , new Token(OperatorType.NUM, "d")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "4")
        };
        Token[] postfix = postfixExpression.infixToPostfix(tokens);
        for (Token token : postfix) {
            System.out.println(token);
        }

        TreeNode treeNode = postfixExpression.treeifyBin(postfix);
        treeNode.printTree();
        BinaryTreeExpression binaryTreeExpression = new BinaryTreeExpression(treeNode);

        Map<String, String> param = new HashMap<>();
        param.put("a", "2");
        param.put("b", "2");
        Assert.assertTrue(binaryTreeExpression.execute(param));
        param.put("a", "1");
        Assert.assertFalse(binaryTreeExpression.execute(param));
    }

    @Test
    public void test4() {
        // 4.99 * 1.06 + 5.99 + 6.99 * 1.06
        // 4.99 1.06 * 5.99 + 6.99 1.06 * +
        PostfixExpression postfixExpression = new PostfixExpression();

        Token[] tokens = {
                new Token(OperatorType.NUM, "4.99")
                , new Token(OperatorType.MULT, "*")

                , new Token(OperatorType.LEFT_MARK, "(")
                , new Token(OperatorType.NUM, "1.06")
                , new Token(OperatorType.ADD, "+")
                , new Token(OperatorType.NUM, "5.99")
                , new Token(OperatorType.RIGHT_MARK, ")")

                , new Token(OperatorType.ADD, "*")
                , new Token(OperatorType.LEFT_MARK, "(")

                , new Token(OperatorType.NUM, "6.99")
                , new Token(OperatorType.MULT, "+")
                , new Token(OperatorType.NUM, "1.06")
                , new Token(OperatorType.RIGHT_MARK, ")")

        };
        Token[] postfix = postfixExpression.infixToPostfix(tokens);
        for (Token token : postfix) {
            System.out.println(token);
        }
    }


    @Test
    public void test5() {
        // (a == 1 && b == 2) || c == 3
        // a 1 == b 2 == && c 3 == ||
        PostfixExpression postfixExpression = new PostfixExpression();
        // a 1 == b 2 ==
        // &&

        Token[] tokens = {
                new Token(OperatorType.LEFT_MARK, "(")
                , new Token(OperatorType.NUM, "a")
                , new Token(OperatorType.NEQ, "!=")
                , new Token(OperatorType.NUM, "1")
                , new Token(OperatorType.AND, "&&")
                , new Token(OperatorType.NUM, "b")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "2")
                , new Token(OperatorType.RIGHT_MARK, ")")
                , new Token(OperatorType.OR, "||")
                , new Token(OperatorType.NUM, "c")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "3")
                , new Token(OperatorType.AND, "&&")
                , new Token(OperatorType.NUM, "d")
                , new Token(OperatorType.EQ, "==")
                , new Token(OperatorType.NUM, "4")
        };
        Token[] postfix = postfixExpression.infixToPostfix(tokens);
        for (Token token : postfix) {
            System.out.println(token);
        }

        TreeNode treeNode = postfixExpression.treeifyBin(postfix);
        treeNode.printTree();
        BinaryTreeExpression binaryTreeExpression = new BinaryTreeExpression(treeNode);

        Map<String, String> param = new HashMap<>();
        param.put("a", "2");
        param.put("b", "2");
        Assert.assertTrue(binaryTreeExpression.execute(param));
        param.put("a", "1");
        Assert.assertFalse(binaryTreeExpression.execute(param));
    }

}
