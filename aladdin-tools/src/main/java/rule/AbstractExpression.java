package rule;

import java.util.Map;

public abstract class AbstractExpression {

    String key;
    String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object execute(Map<String, String> env) {
        return execute0(env);
    }


    public abstract boolean execute0(Map<String, String> env);
}
