package expression;


import java.util.HashMap;
import java.util.Map;

public enum OperatorType {


    AND("&&", 3),
    OR("||", 2),

    EQ("==", 4),
    NEQ("!=", 4),

    NUM("", 0),
    KEY("", 0),
    LEFT_MARK("(", 0),
    RIGHT_MARK(")", 7),
    BIT_AND("&", 8),
    BIT_OR("|", 2),

    NOT("!", 1),

    MULT("*", 8),
    DIV("/", 8),
    MOD("%", 9),
    ADD("+", 7),
    SUB("-", 7),

    LT("<", 4),
    LE("<=", 4),
    GT(">", 4),
    GE(">=", 4),

    ASSIGNMENT("=", 2),

    ;

    static Map<String, OperatorType> operatorTypeMap = new HashMap<>();

    static {
        for (OperatorType operatorType : OperatorType.values()) {
            operatorTypeMap.put(operatorType.symbol, operatorType);
        }
    }

    public static OperatorType fromSymbol(String symbol) {
        OperatorType operatorType = operatorTypeMap.get(symbol);
        if (operatorType == null) {
            throw new IllegalArgumentException(symbol);
        }
        return operatorType;
    }


    String symbol;
    int priority;

    OperatorType(String s, int i) {
        this.symbol = s;
        this.priority = i;
    }

    public static Map<String, OperatorType> getOperatorTypeMap() {
        return operatorTypeMap;
    }

    public static void setOperatorTypeMap(Map<String, OperatorType> operatorTypeMap) {
        OperatorType.operatorTypeMap = operatorTypeMap;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
