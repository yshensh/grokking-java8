package stream;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional类：为了在程序中避免出现空指针异常而创建的。
 *
 * 常用的方法：
 *  - ofNullable(T t)
 *  - orElse(T t)
 *
 */
public class OptionalTest {

    // Optional.of(T t): 创建一个 Optional 实例，t必须非空；
    @Test
    public void test1(){
        Girl girl = new Girl();
        //of(T t):保证t是非空的
        Optional<Girl> optionalGirl = Optional.of(girl);
    }

    @Test
    public void test2(){
        Girl girl = null;
        //ofNullable(T t)：t可以为null
        Optional<Girl> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);
        //orElse(T t1):如果单前的Optional内部封装的t是非空的，则返回内部的t.
        //如果内部的t是空的，则返回orElse()方法中的参数t1.
        Girl girl1 = optionalGirl.orElse(new Girl("G1"));
        System.out.println(girl1);
    }
}
