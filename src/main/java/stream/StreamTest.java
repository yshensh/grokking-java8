package stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *  Stream 执行流程
 *  1. Stream的实例化
 *  2. 一系列的中间操作（1) 过滤 (2)映射 (3)排序
 *  3. 终止操作
 *
 *  说明：
 *  1. 一个中间操作链，对数据源的数据进行处理
 *  2. 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 */

public class StreamTest {
    @Test
    //创建 Stream方式一：通过集合
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();

        // default Stream<E> stream() : 返回一个顺序流
        Stream<Employee> stream = employees.stream();

        // default Stream<E> parallelStream() : 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    //创建 Stream方式二：通过数组
    @Test
    public void test2(){
        // Int型 Stream
        int[] arr1 = new int[]{1,2,3,4,5};
        //调用Arrays类的static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream1 = Arrays.stream(arr1);

        // 自定义型 Stream
        Employee e1 = new Employee(1001,"Tom");
        Employee e2 = new Employee(1002,"Jerry");
        Employee[] arr2 = new Employee[]{e1,e2};
        Stream<Employee> stream2 = Arrays.stream(arr2);
    }

    //创建 Stream方式三：通过Stream的of()
    @Test
    public void test3(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    //创建 Stream方式四：创建无限流
    @Test
    public void test4(){
        //迭代
        //public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        //生成
        //public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 筛选与切片
     * 1） filter(Predicate p) —— 接收 Lambda ， 从流中排除某些元素。
     * 2） limit(n) —— 截断流，使其元素不超过给定数量。
     * 3） skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * 4） distinct() —— 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */

    @Test
    public void test5(){
        List<Employee> list = EmployeeData.getEmployees();
        // 1） filter(Predicate p) —— 接收 Lambda ， 从流中排除某些元素。
        Stream<Employee> stream = list.stream();
        // 查询员工表中薪资大于7000的员工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println();

        // 2） limit(n) —— 截断流，使其元素不超过给定数量。
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();

        // 3） skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();

        // 4） distinct() —— 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        list.add(new Employee(1010,"P10",40,8000));
        list.add(new Employee(1010,"P10",41,8000));
        list.add(new Employee(1010,"P10",40,8000));
        list.add(new Employee(1009,"P10",40,8000));
        list.add(new Employee(1009,"P10",40,8000));
        System.out.println("Full list:");
        System.out.println(list);
        System.out.println("Distinct list:");
        list.stream().distinct().forEach(System.out::println);
    }

    //映射
    @Test
    public void test6() {
        // map(Function f) —— 接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        // flatMap(Function f) —— 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        Stream<Character> characterStream = list.stream().flatMap(StreamTest::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    // 排序
    @Test
    public void test7() {
        // sorted() —— 自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);

        // sorted(Comparator com) —— 定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted( (e1,e2) -> {
            int ageValue = Integer.compare(e1.getAge(),e2.getAge());
            if(ageValue != 0){
                return ageValue;
            }else{
                return -Double.compare(e1.getSalary(),e2.getSalary());
            }
        }).forEach(System.out::println);
    }

    // 匹配与查找
    @Test
    public void test8(){
        List<Employee> employees = EmployeeData.getEmployees();

        // allMatch(Predicate p) —— 检查是否匹配所有元素。
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        // anyMatch(Predicate p) —— 检查是否至少匹配一个元素。
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

        // noneMatch(Predicate p) —— 检查是否没有匹配的元素。
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("1"));
        System.out.println(noneMatch);

        // findFirst —— 返回第一个元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);

        // findAny —— 返回当前流中的任意元素
        Optional<Employee> employee1 = employees.parallelStream().findAny();
        System.out.println(employee1);

        // count —— 返回流中元素的总个数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);

        // max(Comparator c) —— 返回流中最大值
        Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
        Optional<Double> maxSalary = salaryStream.max(Double::compare);
        System.out.println(maxSalary);

        // min(Comparator c) —— 返回流中最小值
        Optional<Employee> minEmployee = employees.stream().min(
                (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())
        );
        System.out.println(minEmployee);

        // forEach(Consumer c) —— 内部迭代
        employees.stream().forEach(System.out::println);
    }

    // 归约
    @Test
    public void test9(){
        //reduce(T identity, BinaryOperator)——可以将流中元素反复结合起来，得到一个值。返回 T
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        // reduce(BinaryOperator) —— 可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        // Optional<Double> sumMoney = salaryStream.reduce(Double::sum);
        Optional<Double> sumMoney = salaryStream.reduce((d1,d2) -> d1 + d2);
        System.out.println(sumMoney);
    }

    // 收集
    @Test
    public void test10() {
        // collect(Collector c) —— 将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream()
                .filter(e -> e.getSalary() > 6000)
                .collect(Collectors.toList());
        employeeList.forEach(System.out::println);

        System.out.println();

        Set<Employee> employeeSet = employees.stream()
                .filter(e -> e.getSalary() > 6000)
                .collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}
