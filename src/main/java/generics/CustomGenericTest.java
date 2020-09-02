package generics;


import org.junit.Test;

import java.util.List;

/**
 *  自定义泛型结构：泛型类、泛型接口；泛型方法。
 */

public class CustomGenericTest {
    @Test
    public void test1(){
        //如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
        //要求：如果大家定义了类是带泛型的，建议在实例化时要指明类的泛型。
        Order order1 = new Order();
        order1.setOrderT(123);
        order1.setOrderT("ABC");

        //建议：实例化时指明类的泛型
        Order<String> order2 = new Order<String>("orderAA",1001,"order:AA");
        order2.setOrderT("AA:hello");
    }

    @Test
    public void test2(){
        SubOrder<String> suborder = new SubOrder<>();
        suborder.setOrderT("order1");
    }

    //测试泛型方法
    @Test
    public void test3(){
        Order<String> order = new Order<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        //泛型方法在调用时，指明泛型参数的类型。
        List<Integer> list = order.copyFromArrayToList(arr);
        System.out.println(list);
    }
}
