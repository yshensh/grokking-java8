package oop;

public class SingletonLazyTest {
    public static void main(String[] args) {
        Order order1 = Order.getInstance();
        Order order2 = Order.getInstance();

        System.out.println(order1 == order2);
    }

}


// 懒汉方式 - 指全局的单例实例在第一次被使用时构建
class Order {

    // 1. 私有化类的构造器
    private Order() {

    }

    // 2. 声明当前类对象，没有初始化instance = new Order();
    // 4。 要求此对象也必须声明为静态的
    private static Order instance = null;

    // 3. 提供公共的静态的方法，返回当前类对象的方法
    public static Order getInstance(){
        if(instance == null) {
            instance = new Order();
        }
        return instance;
    }

}