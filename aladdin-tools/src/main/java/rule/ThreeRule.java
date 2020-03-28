package rule;


import org.jeasy.rules.annotation.*;

@Rule(name = "被3整除", description = "number如果被3整除，打印：number is three")
public class ThreeRule {
    @Condition //条件判断注解：如果return true， 执行Action
    public boolean isThree(@Fact("instOrgCode") String instOrgCode) {
        return !"".equals(instOrgCode);
    }

    @Action(order = 1) //执行方法注解
    public void threeAction(@Fact("instOrgCode") String instOrgCode) {
        System.out.println(instOrgCode);
    }

    @Action(order = 0) //执行方法注解
    public void threeAction2(@Fact("instOrgCode") String instOrgCode){
        System.out.print(instOrgCode + " --------\n");
    }

    @Priority //优先级注解：return 数值越小，优先级越高
    public int getPriority() {
        return 1;
    }
}