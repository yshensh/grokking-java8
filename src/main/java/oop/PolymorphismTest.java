package oop;

/**
 * 对象的多态性： 父类的引用指向子类的对象（可以直接应用在抽象类和接口上）
 *
 * Java 引用变量有两个类型： 1）编译时类型 2）运行时类型
 * 1）编译时类型由生命该变量时使用的类型决定
 * 2）运行时类型由实际赋给该变量的对象决定
 *
 * 总结： 编译时，看左边；运行时， 看右边
 *
 * 若编译时类型和运行时类型不一致，就出现了对象的多态性（Polymorphism)
 * 多态情况下，"看左边"：看的是父类的引用（父类中不具备子类特有的方法）
 *           "看右边"：看的是子类的对象（实际运行的是子类重写父类的方法）
 *
 * 对象的多态性，只适用于方法，不适用于属性（编译和运行都看左边）
 */

public class PolymorphismTest {
    public static void main(String[] args) {
        // 对象的多态性，父类的引用指向子类的对象
        Person person = new Man();
        // (虚拟方法调用 - Virtual Method Invocation)
        // 子类中定义了与父类同名同参数的方法，在多态情况下，将此时父类的方法称为虚拟方法，
        // 父类根据赋给它的不同子类对象，动态调用属于子类的该方法，这样的方法调用在编译时期是无法确定的。
        // 多态的使用， 当调用子父类同名同参数的方法时，实际执行的是子类重写父类的方法
        person.sleep();
        person.work();

        // 多态性的使用
        PolymorphismTest test = new PolymorphismTest();
        test.func(new Man());
        test.func(new Woman());

        // 属性的多态性（看父类的引用）
        System.out.println(person.id); // -1


    }

    public void func(Person person) {
        person.sleep();
        person.work();
    }

}
