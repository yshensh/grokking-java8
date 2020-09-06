package exception;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 一、异常体系结构
 * java.lang.Throwable
 * 		|-----java.lang.Error:一般不编写针对性的代码进行处理。
 * 		|-----java.lang.Exception:可以进行异常的处理
 * 			|------编译时异常(checked)
 * 					|-----IOException
 * 						|-----FileNotFoundException
 * 					|-----ClassNotFoundException
 * 			|------运行时异常(unchecked,RuntimeException)
 * 					|-----NullPointerException
 * 					|-----ArrayIndexOutOfBoundsException
 * 					|-----ClassCastException
 * 					|-----NumberFormatException
 * 					|-----InputMismatchException
 * 					|-----ArithmeticException
 *
 * 二、异常的处理
 * 1. try-catch-finally
 *    try{
 * 		//可能出现异常的代码
 *    }catch(异常类型1 变量名1){
 * 		//处理异常的方式1
 *    }catch(异常类型2 变量名2){
 * 		//处理异常的方式2
 *    }
 *    ....
 *    finally{
 * 		//一定会执行的代码
 *    }
 *
 *    体会1：使用try-catch-finally处理编译时异常，是得程序在编译时就不再报错，但是运行时仍可能报错。
 *          相当于我们使用try-catch-finally将一个编译时可能出现的异常，延迟到运行时出现。
 *    体会2：开发中，由于运行时异常比较常见，所以我们通常就不针对运行时异常编写try-catch-finally了。
 *          针对于编译时异常，我们说一定要考虑异常的处理。
 *
 * 2. throws + 异常类型 写在方法的声明处
 *    指明此方法执行时，可能会抛出的异常类型。一旦当方法体执行时，出现异常，仍会在异常代码处生成一个异常类的对象，
 *    此对象满足throws后异常类型时，就会被抛出。异常代码后续的代码，就不再执行！
 *
 * Summary:
 *    try-catch-finally:真正的将异常给处理掉了。
 *    throws的方式只是将异常抛给了方法的调用者。并没有真正将异常处理掉。
 *
 *    开发中如何选择使用try-catch-finally 还是使用throws？
 *    1) 如果父类中被重写的方法没有throws方式处理异常，则子类重写的方法也不能使用throws，
 *       意味着如果子类重写的方法中有异常，必须使用try-catch-finally方式处理。
 *    2) 执行的方法a中，先后又调用了另外的几个方法，这几个方法是递进关系执行的。我们建议这几个方法使用throws
 *       的方式进行处理。而执行的方法a可以考虑使用try-catch-finally方式进行处理。
 *
 *  三、关于异常对象的产生
 *  1. 系统自动生成的异常对象
 * 	2. 手动的生成一个异常对象，并抛出（throw）
 */
public class ExceptionTest {
    @Test
    public void test1() {
        int res = division(10, 0);
        System.out.println(res);
    }

    public int division (int a, int b){
        try{
            return a / b;
        }catch(ArithmeticException e) {
            e.printStackTrace();
            return 0;
        }
        /*  use case:
            像数据库连接、输入输出流、网络编程Socket等资源，JVM是不能自动的回收的，我们需要自己手动的进行资源的释放。
            此时的资源释放，就需要声明在finally中。
         */
        finally{
            System.out.println("This is executed before return 0 in catch");
        }
    }


    @Test
    public void test2(){
        try {
            method2();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void method1() throws IOException {
        File file = new File("hello.txt");
        FileInputStream fis = new FileInputStream(file);

        int data = fis.read();
        while(data != -1){
            System.out.print((char) data);
            data = fis.read();
        }
        fis.close();
        System.out.println("Done!");
    }

    public static void method2() throws IOException{
        method1();
    }

    @Test
    public void test3(){
        try {
            Student s = new Student();
            s.register(-1001);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    class Student{
        private int id;
        public void register (int id) throws RuntimeException {
            if(id > 0){
                this.id = id;
            } else {
                throw new MyException("id can't be negative!");
            }
        }

        @Override
        public String toString() {
            return "Student [id=" + id + "]";
        }
    }

}
