package designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyStudent<T> implements InvocationHandler {

    T target;

    public DynamicProxyStudent(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(proxy.getClass().getName());
        if (method.getName().equals("giveTask")) {
            System.out.println("动态代理");
        }
        return method.invoke(target, args);
    }
}
