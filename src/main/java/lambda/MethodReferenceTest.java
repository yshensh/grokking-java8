package lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用的使用
 *
 * 1. 使用情境：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
 *
 * 2. 方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例。
 *    所以方法引用，也是函数式接口的实例。
 *
 * 3. 使用格式：类(或对象) :: 方法名
 *
 * 4. 具体分为如下的三种情况：
 *    1) Reference to an instance method of a particular object
 *       containingObject::instanceMethodName
 *       对象::实例方法
 *    2) Reference to a static method
 *       ContainingClass::staticMethodName
 *       类::静态方法
 *    3) 类   :: 非静态方法
 *
 * 5. 方法引用使用的要求：
 *    要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同！（针对于情况1和情况2）
 *
 * 二、构造器引用  (reference to a constructor     ClassNam::new)
 *    和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 *    抽象方法的返回值类型即为构造器所属的类的类型
 *
 * 三、数组引用  (reference to an array)
 *    可以把数组看做是一个特殊的类，则写法与构造器引用一致。
 */
public class MethodReferenceTest {
    // 情况一：对象 :: 实例方法
    // Consumer中的void accept(T t)
    // PrintStream中的void println(T t)
    @Test
    public void test1() {
        // Lambda expression
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("parameter");

        // Lambda expression with method reference
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("new parameter");
    }

    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test2() {
        Employee emp = new Employee(1001,"Tom",23,5600);

        // Lambda expression
        Supplier<String> sup1 = () -> emp.getName();
        System.out.println(sup1.get());

        // Lambda expression with method reference
        Supplier<String> sup2 = emp::getName;
        System.out.println(sup2.get());
    }

    // 情况二：类 :: 静态方法
    //Comparator中的int compare(T t1,T t2)
    //Integer中的int compare(T t1,T t2)
    @Test
    public void test3() {
        // Lambda expression
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1,t2);
        System.out.println(com1.compare(12,21));

        // Lambda expression with method reference
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(12,3));

    }

    // Function中的R apply(T t)
    // Math中的Long round(Double d)
    @Test
    public void test4() {
        // Function
        Function<Double,Long> func = new Function<Double, Long>() {
            @Override
            public Long apply(Double d) {
                return Math.round(d);
            }
        };

        // Lambda expression
        Function<Double,Long> func1 = d -> Math.round(d);
        System.out.println(func1.apply(12.3));

        // Lambda expression with method reference
        Function<Double,Long> func2 = Math::round;
        System.out.println(func2.apply(12.6));
    }

    // 情况三：类 :: 实例方法  (有难度)
    // Comparator中的int comapre(T t1,T t2)
    // String中的int t1.compareTo(t2)
    @Test
    public void test5() {
        // Lambda expression
        Comparator<String> com1 = (s1,s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc","abd"));

        // Lambda expression with method reference
        Comparator<String> com2 = String :: compareTo;
        System.out.println(com2.compare("abd","abm"));
    }

    //BiPredicate中的boolean test(T t1, T t2);
    //String中的boolean t1.equals(t2)
    @Test
    public void test6() {
        // Lambda expression
        BiPredicate<String,String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("abc","abc"));

        // Lambda expression with method reference
        BiPredicate<String,String> pre2 = String :: equals;
        System.out.println(pre2.test("abc","abd"));
    }

    // Function中的R apply(T t)
    // Employee中的String getName();
    @Test
    public void test7() {
        // Lambda expression
        Employee employee = new Employee(1001, "Jerry", 23, 6000);
        Function<Employee,String> func1 = e -> e.getName();
        System.out.println(func1.apply(employee));

        // Lambda expression with method reference
        Function<Employee,String> func2 = Employee::getName;
        System.out.println(func2.apply(employee));
    }

    //构造器引用 (reference to a constructor)
    //Supplier中的T get()
    //Employee的空参构造器：Employee()
    @Test
    public void test8(){
        // Lambda expression 1
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };

        // Lambda expression 2
        Supplier<Employee>  sup1 = () -> new Employee();
        System.out.println(sup1.get());

        // Lambda expression (reference to a constructor)
        Supplier<Employee>  sup2 = Employee :: new;
        System.out.println(sup2.get());
    }

    //Function中的R apply(T t)
    @Test
    public void test9(){
        // Lambda expression
        Function<Integer,Employee> func1 = id -> new Employee(id);
        Employee employee = func1.apply(1001);
        System.out.println(employee);

        // Lambda expression (reference to a constructor)
        Function<Integer,Employee> func2 = Employee :: new;
        Employee employee1 = func2.apply(1002);
        System.out.println(employee1);
    }

    //BiFunction中的R apply(T t,U u)
    @Test
    public void test10(){
        // Lambda expression
        BiFunction<Integer,String,Employee> func1 = (id, name) -> new Employee(id,name);
        System.out.println(func1.apply(1001,"Tom"));

        // Lambda expression (reference to a constructor)
        BiFunction<Integer,String,Employee> func2 = Employee :: new;
        System.out.println(func2.apply(1002,"Tom"));

    }

    //数组引用 (reference to an array)
    //Function中的R apply(T t)
    @Test
    public void test11(){
        // Lambda expression
        Function<Integer,String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(5);
        System.out.println(Arrays.toString(arr1));

        // Lambda expression (reference to an array)
        Function<Integer,String[]> func2 = String[] :: new;
        String[] arr2 = func2.apply(10);
        System.out.println(Arrays.toString(arr2));

    }
}
