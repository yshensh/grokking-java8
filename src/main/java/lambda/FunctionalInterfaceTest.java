package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 *
 * 消费型接口 Consumer<T>     void accept(T t)
 * 供给型接口 Supplier<T>     T get()
 * 函数型接口 Function<T,R>   R apply(T t)
 * 断定型接口 Predicate<T>    boolean test(T t)
 *
 */
public class FunctionalInterfaceTest {
    // 消费型接口 Consumer<T>     void accept(T t)
    public void myConsumer(double money, Consumer<Double> con){
        con.accept(money);
    }

    @Test
    public void test1(){
        // function
        myConsumer(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("price：" + aDouble);
            }
        });
        // Lambda expression
        myConsumer(400, money -> System.out.println("price：" + money));
    }

    // 断定型接口 Predicate<T>    boolean test(T t)
    // 根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }

    @Test
    public void test2(){
        List<String> list = Arrays.asList("one","two","three","four","five");

        // function
        List<String> filterList1 = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("o");
            }
        });
        System.out.println(filterList1);

        // Lambda expression
        List<String> filterList2 = filterString(list,s -> s.contains("o"));
        System.out.println(filterList2);
    }
}
