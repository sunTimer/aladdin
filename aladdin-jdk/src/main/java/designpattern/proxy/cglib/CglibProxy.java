package designpattern.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    public Object intercept(Object object, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        // 添加切面逻辑（advise），此处是在目标类代码执行之前，即为MethodBeforeAdviceInterceptor。
        System.out.println("before-------------");
        // 执行目标类add方法
        proxy.invokeSuper(object, args);
        // 添加切面逻辑（advise），此处是在目标类代码执行之后，即为MethodAfterAdviceInterceptor。
        System.out.println("after--------------");
        return null;
    }
}
