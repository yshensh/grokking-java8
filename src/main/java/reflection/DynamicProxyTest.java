package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Proxy Design Pattern: application on using interface and reflection
 * Dynamic Proxy: allow one single class with one single method to service multiple method calls to arbitrary classes
 * with an arbitrary number of methods. Under the cover, it routes all method invocations to a single handler – the
 * invoke() method.
 *
 * Dynamic proxies can be quite useful for understanding Spring framework and AOP.
 *
 */

interface ClothesFactory {

    void produceClothes();

}

// 被代理类
class NikeClothesFactory implements ClothesFactory {

    @Override
    public void produceClothes() {
        System.out.println("Produce Nike clothes");
    }
}

class ClothesFactoryUtil{
    public void method1() {
        System.out.println("-------------- General method 1 --------------");
    }

    public void method2() {
        System.out.println("-------------- General method 2 --------------");
    }
}


/*
想要实现动态代理， 需要解决的问题
问题一： 如何根据加载到内存钟的被代理类，动态的创建一个代理类以及对象。
问题二： 当通过代理类的对象调用方法时， 如何动态的去调用被代理类中的同名方法。
 */
class ProxyFactory {
    // 调用此方法，返回一个代理类的对象，解决方法一
    public static Object getProxyInstance(Object obj) {
        MyInvocationHandler handler = new MyInvocationHandler();

        handler.bind(obj);

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);

    }

}

class MyInvocationHandler implements InvocationHandler {

    private Object obj; //需要使用使用被代理类的对象进行赋值

    public void bind (Object obj) {
        this.obj = obj;
    }

    // 当我们通过代理类的对象，调用方法时，就会自动的调用如下的方法，invoke()
    // 讲被代理类要执行的方法的功能声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // AOP
        ClothesFactoryUtil util = new ClothesFactoryUtil();
        util.method1();

        // method： 即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        // obj：被代理类的对象
        Object returnValue = method.invoke(obj, args);

        // AOP
        util.method2();

        return returnValue;
    }
}


public class DynamicProxyTest {

    public static void main(String[] args) {
        NikeClothesFactory nikeFactory = new NikeClothesFactory();
        // proxyInstance: 代理类的对象
        ClothesFactory proxyInstance = (ClothesFactory) ProxyFactory.getProxyInstance(nikeFactory);

        // 当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        proxyInstance.produceClothes();
    }
}