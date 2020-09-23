package collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *  Collection (interface)
 *  1) List (interface): An ordered collection (also known as a sequence).
 *     Some important implementing classes: ArrayList, LinkedList, Vector
 *  2) Set (interface): A collection that contains no duplicate elements.
 *     Some important implementing classes: HashSet, LinkedHashSet, TreeSet
 *
 *  结论：向Collection接口的实现类的对象中添加数据obj时，要求obj所在类要重写equals().
 */

public class CollectionTest {
    /**
     * contains(Object obj):判断当前集合中是否包含obj
     * 我们在判断时会调用obj对象所在类的equals()。
     */
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(4567);
        coll.add(new Person("Jerry",20));
        coll.add("Tom");
        coll.add(false);
        System.out.println("Call contains() on number");
        System.out.println(coll.contains(123));
        System.out.println("Call contains() on Person");
        System.out.println(coll.contains(new Person("Jerry",20)));
    }

    /**
     * remove(Object obj):从当前集合中移除obj元素。
     */
    @Test
    public void test2(){
        //3.
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add("Tom");
        coll.add(false);

        System.out.println("Call removes() on number");
        coll.remove(1234);
        System.out.println(coll);

        System.out.println("Call removes() on Person");
        coll.remove(new Person("Jerry",20));
        System.out.println(coll);
    }

    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        // 集合 --->数组：toArray()
        Object[] arr = coll.toArray();
        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }

        // 数组 --->集合:调用Arrays类的静态方法asList()
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        System.out.println(list);

        List arr1 = Arrays.asList(new int[]{123, 456});
        System.out.println(arr1.size());// 1

        List arr2 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr2.size());// 2
    }
}
