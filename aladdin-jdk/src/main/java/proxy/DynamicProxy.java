package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] args) {
        BeautifulSing sing = new BeautifulSing();
        Sing proxyInstance = (Sing) Proxy.newProxyInstance(sing.getClass().getClassLoader(), new Class[]{Sing.class}, new SingProxy(sing));
        proxyInstance.sing();

    }
}


class SingProxy implements InvocationHandler {

    public Sing target;

    public SingProxy(Sing target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret;
        if (method.getName().equals("sing")) {
            System.out.println("before");
            ret = method.invoke(args);
            System.out.println("after");
            return ret;
        }
        return method.invoke(args);
    }
}

interface Sing {
    void sing();
}

class BeautifulSing implements Sing {

    @Override
    public void sing() {
        System.out.println("sing...");
    }
}