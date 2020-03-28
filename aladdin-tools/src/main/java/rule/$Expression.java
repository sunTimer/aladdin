package rule;

import java.util.Map;

public class $Expression extends AbstractExpression {

    @Override
    public boolean execute0(Map<String, String> env) {
        return env.get("instOrgCode").equals("aliPay");
    }
}
