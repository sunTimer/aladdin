package rule.milk;


import java.util.HashMap;
import java.util.Map;

public enum OperatorType {

    EQ("==", 1),
    NEQ("!=", 2),
    AND("&&", 3),
    OR("||", 4),
    VALUE("", 5),
    LEFT_MARK("(", 6),
    RIGHT_MARK(")", 7),
    SINGLE_AND("&", 8);

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
    int type;

    OperatorType(String s, int i) {
        this.symbol = s;
        this.type = i;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
