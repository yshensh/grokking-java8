package collections;

import org.junit.Test;

import java.util.*;

/**
 *  Map (interface): An object that maps keys to values.
 *  Some important implementing classes: HashMap, LinkedHashMap, TreeMap, Hashtable, Properties
 *
 *  Map接口:双列数据，存储key-value对的数据
 *  - HashMap:作为Map的主要实现类；线程不安全的，效率高；存储null的key和value
 *      HashMap的底层：数组+链表（jdk7及之前）数组+链表+红黑树（jdk 8）
 *      - LinkedHashMap:保证在遍历map元素时，可以按照添加的顺序实现遍历。
 *        原因：在原有的HashMap底层结构基础上，添加了一对指针，指向前一个和后一个元素。
 *        对于频繁的遍历操作，此类执行效率高于HashMap。
 *  - TreeMap:保证按照添加的key-value对进行排序，实现排序遍历。
 *    此时考虑key的自然排序或定制排序底层使用红黑树
 *  - Hashtable:作为古老的实现类；线程安全的，效率低；不能存储null的key和value
 *      - Properties:常用来处理配置文件。key和value都是String类型
 *
 *  Map结构的理解 （以HashMap为例）：
 *  - Map中的key:无序的、不可重复的，使用Set存储所有的key  ---> key所在的类要重写equals()和hashCode()
 *  - Map中的value:无序的、可重复的，使用Collection存储所有的value --->value所在的类要重写equals()
 *  - 一个键值对：key-value构成了一个Entry对象。
 *  - Map中的entry:无序的、不可重复的，使用Set存储所有的entry
 *
 *  HashMap的底层实现原理？(以jdk7为例说明)：
 *      HashMap map = new HashMap():
 *  在实例化以后，底层创建了长度是16的一维数组Entry[] table。
 *  (...可能已经执行过多次put...)
 *  map.put(key1,value1):
 *  首先，调用key1所在类的hashCode()计算key1哈希值，此哈希值经过某种算法计算以后，得到在Entry数组中的存放位置。
 *  1) 如果此位置上的数据为空，此时的key1-value1添加成功。 (case 1)
 *  2) 如果此位置上的数据不为空，(意味着此位置上存在一个或多个数据(以链表形式存在)),比较key1和已经存在的一个或多个数据的哈希值：
 *      a) 如果key1的哈希值与已经存在的数据的哈希值都不相同，此时key1-value1添加成功。(case 2)
 *      b) 如果key1的哈希值和已经存在的某一个数据(key2-value2)的哈希值相同，继续比较：调用key1所在类的equals(key2)方法，比较：
 *         - 如果equals()返回false:此时key1-value1添加成功。(case 3)
 *         - 如果equals()返回true:使用value1替换value2。
 *  补充：关于情况2和情况3：此时key1-value1和原来的数据以链表的方式存储。
 *  在不断的添加过程中，会涉及到扩容问题，当超出临界值(且要存放的位置非空)时，扩容。默认的扩容方式：扩容为原来容量的2倍，并将原有的数据复制过来。
 *  (jdk8 相较于jdk7在底层实现方面的不同)：
 *  1. new HashMap():底层没有创建一个长度为16的数组
 *  2. jdk 8底层的数组是：Node[],而非Entry[]
 *  3. 首次调用put()方法时，底层创建长度为16的数组
 *  4. jdk7底层结构只有：数组+链表。jdk8中底层结构：数组+链表+红黑树。
 *      a) 形成链表时，七上八下（jdk7:新的元素指向旧的元素。jdk8:旧的元素指向新的元素）
 *      b) 当数组的某一个索引位置上的元素以链表形式存在的数据个数 > 8 且当前数组的长度 > 64时，此时此索引位置上的所数据改为使用红黑树存储。
 *
 *  LinkedHashMap的底层实现原理
 * （source code）
 *  static class Entry<K,V> extends HashMap.Node<K,V> {
 *      Entry<K,V> before, after;//能够记录添加的元素的先后顺序
 *      Entry(int hash, K key, V value, Node<K,V> next) {
 *          super(hash, key, value, next);
 *      }
 *   }
 *
 *  总结：常用方法：
 *  - 添加：put(Object key,Object value)
 *  - 删除：remove(Object key)
 *  - 修改：put(Object key,Object value)
 *  - 查询：get(Object key)
 *  - 长度：size()
 *  - 遍历：keySet() / values() / entrySet()
 */

public class MapTest {
    //向TreeMap中添加key-value，要求key必须是由同一个类创建的对象
    //要按照key进行排序：1)自然排序 2)定制排序

    //1) 自然排序
    @Test
    public void test1(){
        TreeMap map = new TreeMap();
        User u1 = new User("Tom",23);
        User u2 = new User("Jerry",32);
        User u3 = new User("Jack",20);
        User u4 = new User("Rose",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);

        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());
        }
    }

    //2) 定制排序
    @Test
    public void test2(){
        TreeMap map = new TreeMap(new Comparator() {
            // 定制排序: 按照年龄从小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    return Integer.compare(u1.getAge(),u2.getAge());
                }
                throw new RuntimeException("输入的类型不匹配！");
            }
        });
        User u1 = new User("Tom",23);
        User u2 = new User("Jerry",32);
        User u3 = new User("Jack",20);
        User u4 = new User("Rose",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);

        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while (iterator1.hasNext()){
            Object obj = iterator1.next();
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + "---->" + entry.getValue());

        }
    }
}
