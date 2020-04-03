package rule;

import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.MapContext;

public class FelRuleEngine {
    public static void main(String[] args) {
        FelEngine fel = new FelEngineImpl();
        Object result = fel.eval("5000*12+7500");
        MapContext context = new MapContext();
        context.set("a", "100");
        Expression expression = fel.compile("a == '1' && b != '3'", context);

        Object eval = expression.eval(context);
        System.out.println(eval);
    }
}