package sentinel;

import com.alibaba.csp.sentinel.*;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Sentinel {

    public static void main(String[] args) throws InterruptedException {
        // 配置规则.
        initFlowRules();

//        while (true) {
//            // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
//            try (Entry entry = SphU.entry("HelloWorld")) {
//                // 被保护的逻辑
//                System.out.println("hello world".indexOf("dd"));
//            } catch (BlockException ex) {
//                // 处理被流控的逻辑
//                System.out.println("blocked!");
//            }
//        }

        Entry entry = null;
        try {
            entry = SphU.entry("key", EntryType.IN, 6);

            // Write your biz code here.
            // <<BIZ CODE>>
        } catch (Throwable t) {
            if (!BlockException.isBlockException(t)) {
                Tracer.trace(t);
            }
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }


        while (true) {

            if (SphO.entry("HelloWorld")) {
                try {
                    System.out.println("hello world");
                } finally {
                    SphO.exit();
                }
            } else {
                System.out.println("blocked");
            }
//
//            // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
//            try (Entry entry = SphU.entry("HelloWorld")) {
//                // 被保护的逻辑
//                System.out.println("hello world".indexOf("dd"));
//            } catch (BlockException ex) {
//                // 处理被流控的逻辑
//                System.out.println("blocked!");
//            }
        }
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(200);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}


class DoSomething {

    public static void main(String[] args) {
        System.out.println("hello world");
    }
}