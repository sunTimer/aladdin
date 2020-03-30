package expression;

import rule.milk.OperatorType;

public class Token implements Comparable<Token> {
    /**
     * 1 操作数
     * 2 操作符
     */
    OperatorType type;
    String value;


    public Token(OperatorType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    boolean isNum() {
        return type == OperatorType.NUM;
    }

    boolean isLeftMark() {
        return type == OperatorType.LEFT_MARK;
    }

    boolean isRightMark() {
        return type == OperatorType.RIGHT_MARK;
    }

    @Override
    public int compareTo(Token o) {
        return this.type.getPriority() - o.type.getPriority();
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                '}';
    }
}


