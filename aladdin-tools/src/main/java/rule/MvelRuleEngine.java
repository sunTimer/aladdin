package rule;

import org.junit.Test;
import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;

public class MvelRuleEngine {

    public static void main(String[] args) {
        String expression = "instOrgCode == '00' && bankOrgCode != '01'";
        Map<String, Object> vars = new HashMap<>();
        vars.put("instOrgCode", "00");
        vars.put("bankOrgCode", "011");
        // We know this expression should return a boolean.
        Boolean result = (Boolean) MVEL.eval(expression, vars);
        if (result) {
            System.out.println("It works!");
        } else {
            System.out.println("no works");
        }
    }

    @Test
    public void function(){
        String expression = "def addTwo(num1, num2) { num1 + num2; } val = addTwo(a, b);";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a", 2);
        paramMap.put("dd", "dfsf");
        paramMap.put("b",34);
        Object object = MVEL.eval(expression, paramMap);
        System.out.println(object); //
    }
}
