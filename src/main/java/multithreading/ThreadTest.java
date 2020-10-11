package multithreading;

/**
 * 多线程的创建
 * 方式一：继承于Thread类
 * 1. 创建一个继承于Thread类的子类
 * 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start()
 *
 * 方式二：实现Runnable接口
 * 1. 创建一个实现了Runnable接口的类
 * 2. 实现类去实现Runnable中的抽象方法：run()
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 5. 通过Thread类的对象调用start()
 *
 * 比较创建线程的两种方式。
 * 开发中，优先选择：实现Runnable接口的方式
 * 原因：1. 方式二实现的方式没有类的单继承性的局限性
 *      2. 方式二实现的方式更适合来处理多个线程有共享数据的情况。
 *
 * 联系：public class Thread implements Runnable
 * 相同点：两种方式都需要重写run(),将线程要执行的逻辑声明在run()中。
 */

//方式一：继承于Thread类
//1. 创建一个继承于Thread类的子类
class MyThreadOne extends Thread {
    //2. 重写Thread类的run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println("方式一：继承于Thread类  " + Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

//方式二：实现Runnable接口
//1. 创建一个实现了Runnable接口的类
class MyThreadTwo implements Runnable{

    //2. 实现类去实现Runnable中的抽象方法：run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println("方式二：实现Runnable接口  " + Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
        //方式一：继承于Thread类
        //3. 创建Thread类的子类的对象
        MyThreadOne t11 = new MyThreadOne();

        //4.通过此对象调用start(): 1)启动当前线程 2)调用当前线程的run()
        t11.start();

        //问题一：我们不能通过直接调用run()的方式启动线程。
        // t1.run();

        //问题二：再启动一个线程，遍历100以内的偶数。不可以还让已经start()的线程去执行。会报IllegalThreadStateException
        // t1.start();
        // 我们需要重新创建一个线程的对象
        MyThreadOne t12 = new MyThreadOne();
        t12.start();

        //如下操作仍然是在main线程中执行的。
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println("[main thread] " + Thread.currentThread().getName() + ":" + i);
            }
        }


        //方式二：实现Runnable接口
        //3. 创建实现类的对象
        MyThreadTwo t2 = new MyThreadTwo();
        //4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        Thread t21 = new Thread(t2);
        t21.setName("线程1");
        //5. 通过Thread类的对象调用start():
        // 1) 启动线程
        // 2) 调用当前线程的run()-->调用了Runnable类型的target的run()
        t21.start();

        //再启动一个线程，遍历100以内的偶数
        Thread t22 = new Thread(t2);
        t22.setName("线程2");
        t22.start();
    }
}
