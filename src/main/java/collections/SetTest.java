package collections;

import org.junit.Test;

import java.util.*;

/**
 * Set接口：存储无序的、不可重复的数据
 *  - HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
 *      - LinkedHashSet：作为HashSet的子类；
 *                       遍历其内部数据时，可以按照添加的顺序遍历对于频繁的遍历操作，
 *                       LinkedHashSet效率高于HashSet.
 *  - TreeSet：可以按照添加对象的指定属性，进行排序。
 *      1) 添加的数据，要求是相同类
 *      2）两种排序方式：自然排序（实现Comparable接口）和定制排序（Comparator）
 *      3) 自然排序中，比较两个对象是否相同的标准为：compareTo()返回0。不再是equals()
 *      4) 定制排序中，比较两个对象是否相同的标准为：compare()返回0。不再是equals().
 *
 *  1) 无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是根据数据的哈希值决定的。
 *  2) 不可重复性：保证添加的元素按照equals()判断时，不能返回true.即：相同的元素只能添加一个。
 *
 *  添加元素的过程(以HashSet为例)：
 *  我们向HashSet中添加元素a,首先调用元素a所在类的hashCode()方法，计算元素a的哈希值，
 *  此哈希值接着通过某种算法计算出在HashSet底层数组中的存放位置（即为：索引位置），判断
 *  数组此位置上是否已经有元素：
 *  1) 如果此位置上没有其他元素，则元素a添加成功。(case 1)
 *  2) 如果此位置上有其他元素b(或以链表形式存在的多个元素），则比较元素a与元素b的hash值：
 *     - 如果hash值不相同，则元素a添加成功。(case 2)
 *     - 如果hash值相同，进而需要调用元素a所在类的equals()方法：
 *          equals()返回true,元素a添加失败
 *          equals()返回false,则元素a添加成功 (case 3)
 *  HashSet底层：数组+链表的结构。
 *  对于添加成功的(case 2)和(case 3)而言：元素a 与已经存在指定索引位置上数据以链表的方式存储。
 *
 *  jdk 7 :元素a放到数组中，指向原来的元素。
 *  jdk 8 :原来的元素在数组中，指向元素a
 *  总结：七上八下
 *
 *  在向Set中添加的数据
 *  1）其所在的类一定要重写hashCode()和equals()
 *  2) 重写的hashCode()和equals()尽可能保持一致性，相等的对象必须具有相等的散列码
 *     重写两个方法的小技巧：对象中用作equals()方法比较的Field，都应该用来计算hasCode （IDE can auto generate both methods)
 *
 */
public class SetTest {
    //自然排序（实现Comparable接口:按照姓名从大到小排列,年龄从小到大排列
    @Test
    public void test1(){
        Set<User> set = new TreeSet<>();

        set.add(new User("Tom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));

        for (User user : set) {
            System.out.println(user);
        }
    }

    @Test
    public void test2(){
        //定制排序: 按照年龄从小到大排列
        Comparator<User> com = (o1, o2) -> {
            if(o1 != null && o2 != null){
                return Integer.compare(o1.getAge(),o2.getAge());
            }else{
                throw new RuntimeException("输入的数据类型不匹配");
            }
        };

        TreeSet<User> set = new TreeSet<User>(com);
        set.add(new User("Tom",12));
        set.add(new User("Jerry",32));
        set.add(new User("Jim",2));
        set.add(new User("Mike",65));
        set.add(new User("Mary",33));
        set.add(new User("Jack",33));
        set.add(new User("Jack",56));

        for (User user : set) {
            System.out.println(user);
        }
    }
}
