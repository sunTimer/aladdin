package rule;


import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;

public class EasyRuleEngine {
    public static void main(String[] args) {
        /**
         * 创建规则执行引擎
         * 注意: skipOnFirstAppliedRule意思是，只要匹配到第一条规则就跳过后面规则匹配
         */
        RulesEngineParameters parameters = new
                RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        //创建规则
        Rules rules = new Rules();

        rules.register(new ThreeRule());
        Facts facts = new Facts();
        for (int i = 1; i <= 9; i++) {
            //规则因素，对应的name，要和规则里面的@Fact 一致
            facts.put("instOrgCode", "000000" + i);
            //执行规则
            rulesEngine.fire(rules, facts);
        }
    }
}

