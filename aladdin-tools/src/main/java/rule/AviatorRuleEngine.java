package rule;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AviatorRuleEngine {

    static Map<String, Integer> rules = new HashMap<>();
    static Map<Expression, String> expressionMap = new HashMap<>();
    static ExecutorService executors = Executors.newFixedThreadPool(4);

    static {
        rules.put("instOrgCode == 'alipay' || msgTp == 'epcc.101.001.01'", 100);
        rules.put("instOrgCode == 'tenpay' && msgTp == 'epcc.201.001.01'", 50);
        rules.put("instOrgCode != 'tenpay' && instOrgCode != 'alipay' && msgTp == 'epcc.201.001.01'", 10);
        rules.put("idc != '10'", 10000);
        for (int i = 0; i < 200000; i++) {
            rules.put(String.format("(idc != '%d' || name == 'sunny') && instOrgCode == '%s'", i, i), i);
        }

        for (String key : rules.keySet()) {
            expressionMap.put(AviatorEvaluator.compile(key, true), key);
        }

        System.out.println("init rules size = " + expressionMap.size());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final Map<String, Object> env = new HashMap<>();
        env.put("instOrgCode", "alipay");
        env.put("msgTp", "epcc.201.001.01");
        env.put("idc", "10");
        env.put("drctn", "22");
        env.put("acctTp", "01");
        env.put("bankOrgCode", "C0000000");

        for (int i = 0; i < 1000000; i++) {
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    long start = System.nanoTime();
                    match(env);
                    System.out.println("thread = [" + Thread.currentThread().getName() + "]   cost = " + (System.nanoTime() - start) / (1000.0 * 1000));
                }
            });
        }
    }


    private static String match(Map<String, Object> env) {
        for (Map.Entry<Expression, String> entry : expressionMap.entrySet()) {
            Object ret = entry.getKey().execute(env);
        }
        return "";
    }

    @Test
    public void test() {
        Map<String, Object> param = new HashMap<>();
        param.put("instOrgCode", "aliPay");
        Object ret = AviatorEvaluator.execute("instOrgCode", param);// true
        System.out.println(AviatorEvaluator.execute("string.contains(\"inst\", 'instOrgCode')"));
        System.out.println(ret);
    }
}
