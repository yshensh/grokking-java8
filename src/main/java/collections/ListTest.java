package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *  ArrayList、LinkedList、Vector三者的异同？
 *  同：三个类都是实现了List接口，存储数据的特点相同：存储有序的、可重复的数据
 *  异：
 *  - ArrayList：作为List接口的主要实现类；线程不安全的，效率高；底层使用 Object[] elementData 存储
 *  - LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高；底层使用双向链表存储
 *  - Vector：作为List接口的古老实现类；线程安全的，效率低；底层使用 Object[] elementData 存储
 *
 *  1) ArrayList的源码分析：
 *      jdk 7情况下
 *          ArrayList list = new ArrayList();//底层创建了长度是10的Object[]数组elementData
 *          list.add(123);//elementData[0] = new Integer(123);
 *          ...
 *          list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。
 *          默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *
 *          结论：建议开发中使用带参的构造器：ArrayList list = new ArrayList(int capacity)
 *
 *      jdk 8中ArrayList的变化：
 *          ArrayList list = new ArrayList();//底层Object[] elementData初始化为{}.并没有创建长度为10的数组
 *
 *          list.add(123);//第一次调用add()时，底层才创建了长度10的数组，并将数据123添加到elementData[0]
 *          ...
 *          后续的添加和扩容操作与jdk 7 无异。
 *
 *      小结：jdk7中的ArrayList的对象的创建类似于单例的饿汉式，而jdk8中的ArrayList的对象的创建类似于单例的懒汉式，
 *           延迟了数组的创建，节省内存。
 *
 *  2) LinkedList的源码分析：
 *     内部声明了Node类型的first和last属性，默认值为null
 *
 *  3) Vector的源码分析：
 *     通过Vector()构造器创建对象时，底层都创建了长度为10的数组。
 *     在扩容方面，默认扩容为原来的数组长度的2倍。
 *
 *  常用方法
 *  - 增：add(Object obj)
 *  - 删：remove(int index) / remove(Object obj)
 *  - 改：set(int index, Object ele)
 *  - 查：get(int index)
 *  - 插：add(int index, Object ele)
 *  - 长度：size()
 *  - 遍历: 1) Iterator迭代器方式
 *         2) 增强for循环
 *         3) 普通的循环
 *
 */

public class ListTest {
    List<Object> l1 = new LinkedList<>();

    List<Object> l2 = new Vector<>();
}
