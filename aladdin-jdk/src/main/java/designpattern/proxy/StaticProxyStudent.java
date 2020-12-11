package designpattern.proxy;

public class StaticProxyStudent implements Student {

    Student target;

    public StaticProxyStudent(Student target) {
        this.target = target;
    }

    @Override
    public void giveTask() {
        System.out.println("静态代理");
        target.giveTask();
    }
}
