package designpattern.proxy;

public class GoodStudent implements Student {

    @Override
    public void giveTask() {
        System.out.println("good student do task.");
    }
}
