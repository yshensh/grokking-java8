package annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * 注解的使用
 *
 * 1 如何自定义 Annotation: 参照@SuppressWarnings定义
 *  1）注解声明为： @interface
 *  2）内部定义成员，通常使用value表示
 *  3）可以指定成员的默认值，使用default定义
 *  4）如果自定义注解没有成员，表明是一个标识作用
 *
 * 如果注解由成员，在使用注解时，需要指明成员的值。
 * 自定义注解必须配上注解的信息处理流程（使用反射）才有意义。
 * 自定义注解通常都会指明两个元注解：Retention, Target
 *
 * 2 JDK中提供的四种元注解(meta-annotation)
 * 元注解：对现有的注解进行解释说明的注解
 *  1）Retention： 指定所修饰的Annotation的生命周期 SOURCE/CLASS(默认)/RUNTIME
 *    只有声明为RUNTIME生命周期的注解，才能通过反射获取。
 *  2) Target：用于指定被修饰的 Annotation 能用于修饰哪些程序元素
 *  3) Documented：表示所修饰的注解在被javadoc解析式，被保留下来
 *  4) Inherited：被它修饰的Annotation将具有继承性
 *
 * 3.　通过反射获取注解信息 -- 到反射内容时详细解释
 *
 * 4. JDK 8中注解的新特性：可重复注解、类型注解
 * 4.1 可重复注解
 *  1）在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
 *  2）MyAnnotation的Target和Retention等元注解与MyAnnotations相同。
 * 4.2 类型注解
 *  ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
 *  ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Person p = new Student();
        p.walk();
    }

    @Test
    // demonstration on @Inherited - Student inherits the annotation from Person
    public void testGetAnnotation() {
        Class<Student> studentClass = Student.class;
        Annotation[] annotations = studentClass.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
    }
}

@MyAnnotation(value="hi")
@MyAnnotation(value="abc")
// Before JDK 8
// @MyAnnotations({@MyAnnotation(value="hi"), @MyAnnotation(value="abc")})
class Person {

    private String name;
    public int age;

    public Person() {
    }

    @MyAnnotation
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk() {
        System.out.println("walk");
    }
}

interface Info{
    void show();
}

class Student extends Person implements Info{

    @Override
    public void walk() {
        System.out.println("students are walking");
    }

    public void show() {

    }
}

class Generic<@MyAnnotation T> {

    public void show() throws @MyAnnotation RuntimeException{
        ArrayList<@MyAnnotation String> list = new ArrayList<>();

        int num = (@MyAnnotation int) 10L;
    }
}