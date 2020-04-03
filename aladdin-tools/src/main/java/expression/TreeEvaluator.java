package expression;

import java.util.List;

/**
 * <pre>
 *     m == 2 && n != 4 && h == 1
 *                    &&
 *                /       |
 *              ==       &&
 *            /   |    /    |
 *          m     2   !=    ==
 *                   /  |  / |
 *                  n   4  h  1
 * </pre>
 */
public class TreeEvaluator {

    private PostfixExpression postfixExpression = new PostfixExpression();

    private Lexer lexer = new Lexer();

    public TreeExpression compile(String expressionStr) {
        List<Token> postfix = postfixExpression.infixToPostfix(lexer.parse(expressionStr));
        TreeNode root = postfixExpression.treeifyBin(postfix);
        return new TreeExpression(root);
    }
}
