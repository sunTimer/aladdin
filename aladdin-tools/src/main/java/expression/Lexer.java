package expression;

import java.util.ArrayList;
import java.util.List;

/**
 * 词法解析器
 * from : "a==1&&b==2||c==3"
 * to   : ["a", "==", "1", "&&", "b", "==", "2", "||", "c", "==", "3"]
 */
public final class Lexer {

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        String seq = "(name == epcc.101.001.01 && (from == beijing || age != 23)";
        seq = "(aa == 11)";
        List<Token> tokens = lexer.parse(seq);
        for (Token token : tokens) {
            System.out.println(token);
        }
    }


    public final List<Token> parse(final String seq) {
        List<Token> tokens = new ArrayList<>();
        char[] charArray = seq.toCharArray();
        char next;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            switch (c) {
                case ' ':
                    break;
                case '(':
                    tokens.add(new Token(OperatorType.LEFT_MARK, "("));
                    break;
                case ')':
                    tokens.add(new Token(OperatorType.RIGHT_MARK, ")"));
                    break;
                case '=':
                    next = charArray[i + 1];
                    if (next == '=') {
                        tokens.add(new Token(OperatorType.EQ, "=="));
                        i++;
                    } else {
                        tokens.add(new Token(OperatorType.ASSIGNMENT, "="));
                    }
                    break;
                case '!':
                    next = charArray[i + 1];
                    if (next == '=') {
                        tokens.add(new Token(OperatorType.NEQ, "!="));
                        i++;
                    } else {
                        tokens.add(new Token(OperatorType.NOT, "!"));
                    }
                    break;
                case '&':
                    next = charArray[i + 1];
                    if (next == '&') {
                        tokens.add(new Token(OperatorType.AND, "&&"));
                        i++;
                    } else {
                        tokens.add(new Token(OperatorType.BIT_AND, "&"));
                    }
                    break;
                case '|':
                    next = charArray[i + 1];
                    if (next == '|') {
                        tokens.add(new Token(OperatorType.OR, "||"));
                        i++;
                    } else {
                        tokens.add(new Token(OperatorType.BIT_OR, "|"));
                    }
                    break;
                default:
                    int right = seq.length();
                    StringBuilder tmp = new StringBuilder();
                    while (i < right) {
                        next = charArray[i];
                        if (isChar(next)) {
                            tmp.append(next);
                            i++;
                        } else {
                            i--;
                            break;
                        }
                    }
                    tokens.add(new Token(OperatorType.NUM, tmp.toString()));
            }
        }

        return tokens;
    }

    private boolean isChar(char c) {
        return c >= 'a' && c <= 'z'
                || c >= 'A' && c <= 'Z'
                || c >= '0' && c <= '9'
                || c == '.';
    }
}
