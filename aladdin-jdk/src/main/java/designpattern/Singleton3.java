package designpattern;

public enum Singleton3 {

    INSTANCE;


    public void doSomething() {
        // work
        System.out.println("ok");
    }

    public static void main(String[] args) {
        Singleton3.INSTANCE.doSomething();
    }
}
