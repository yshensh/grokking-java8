package oop;

public class SingletonHungryTest {
    public static void main(String[] args) {
        Bank bank1 = Bank.getInstance();
        Bank bank2 = Bank.getInstance();

        System.out.println(bank1 == bank2);
    }

}


// 饿汉方式 （线程安全）- 指全局的单例实例在类装载时构建
class Bank {

    // 1. 私有化类的构造器
    private Bank() {

    }

    // 2. 内部创建类的对象
    // 4。 要求此对象也必须声明为静态的
    private static Bank instance = new Bank();

    // 3. 提供公共的静态的方法，返回类的对象
    public static Bank getInstance(){
        return instance;
    }

}