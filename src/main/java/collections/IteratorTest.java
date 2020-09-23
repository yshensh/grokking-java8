package collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，使用迭代器Iterator接口
 * (For Collection not for Map)
 * 1.内部的方法：hasNext() 和  next()
 * 2.集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
 *   默认游标都在集合的第一个元素之前。
 * 3.内部定义了remove
 */
public class IteratorTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();

        // recommended
        // hasNext():判断是否还有下一个元素
        while(iterator.hasNext()){
            // next(): 1) 指针下移 2) 将下移以后集合位置上的元素返回
            System.out.println(iterator.next());
        }

        // error - always print out the same element
        while (coll.iterator().hasNext()){
            System.out.println(coll.iterator().next());
        }
    }
}
