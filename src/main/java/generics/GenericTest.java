package generics;

import org.junit.Test;

import java.util.*;


/**
 * 在集合中使用泛型：
 *  1. 集合接口或集合类在JDK5.0时都修改为带泛型的结构。
 *  2. 在实例化集合类时，可以指明具体的泛型类型
 *  3. 指明完以后，在集合类或接口中凡是定义类或接口时，内部结构（比如：方法、构造器、属性等）使用到类的泛型的位置，都指定为实例化的泛型类型。
 *     比如：add(E e) --->实例化以后：add(Integer e)
 *  4. 注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，拿包装类替换
 *  5. 如果实例化时，没有指明泛型的类型。默认类型为java.lang.Object类型。
 */

public class GenericTest {
    //在集合中使用泛型之前的情况：
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        //需求：存放学生的成绩
        list.add(78);
        list.add(76);
        //问题一：类型不安全
        list.add("Tom");

        for(Object score : list){
            //问题二：强转时，可能出现ClassCastException
            int stuScore = (Integer) score;
            System.out.println(stuScore);
        }
    }

    //在集合中使用泛型的情况：以ArrayList为例
    @Test
    public void test2(){
        ArrayList<Integer> list =  new ArrayList<Integer>();

        list.add(78);
        list.add(87);
        // 编译时，就会进行类型检查，保证数据的安全
        // list.add("Tom");

        //方式一：
        for(Integer score : list){
            //避免了强转操作
            int stuScore = score;
            System.out.println(stuScore);
        }

        //方式二：
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }

    //在集合中使用泛型的情况：以HashMap为例
    @Test
    public void test3(){
        //Map<String,Integer> map = new HashMap<String,Integer>();
        //JDK7新特性：类型推断
        Map<String,Integer> map = new HashMap<>();

        map.put("Tom",87);
        map.put("Jerry",87);
        // map.put(123,"ABC");

        //泛型的嵌套
        Set<Map.Entry<String,Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();

        while(iterator.hasNext()){
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "->" + value);
        }
    }

    // 通配符的使用
    // 通配符：?
    // 类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
    @Test
    public void test4(){
        List<Object> list1 = null;
        List<String> list2 = null;
        List<?> list = null;

        list = list1;
        list = list2;

        // how to use
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(写入)：对于List<?>就不能向其内部添加数据。
        //除了添加null之外。
        // list.add("DD");
        // list.add('?');
        list.add(null);

        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }

    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /*
    3.有限制条件的通配符的使用。
        ? extends A:
        G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类

        ? super A:
        G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类

     */
    @Test
    public void test5(){

        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        /*
            ? extends A: (-inf, A]
            G<? extends A> 可以作为G<A>和G<B>的父类，其中B是A的子类
         */
        list1 = list3;
        list1 = list4;
        // list1 = list5;

        /*
            ? super A: [A, +inf)
            G<? super A> 可以作为G<A>和G<B>的父类，其中B是A的父类
        */
        // list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据：
        list1 = list3;
        Object person1 = list1.get(0);
        Person person2 = list1.get(0);
        //编译不通过
        //Student person3 = list1.get(0);

        list2 = list4;
        Object obj = list2.get(0);
        //编译不通过
        // Person obj = list2.get(0);

        //写入数据：
        //编译不通过
        // list1.add(new Student());

        //编译通过
        list2.add(new Person());
        list2.add(new Student());
    }

}
