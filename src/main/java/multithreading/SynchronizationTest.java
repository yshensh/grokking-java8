package multithreading;

/**
 *  在Java中，通过同步机制，来解决线程的安全问题。
 *  好处: 同步的方式，解决了线程的安全问题。
 *  局限性: 操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。
 *
 *  方式一：同步代码块
 *   synchronized(同步监视器){
 *      //需要被同步的代码
 *
 *   }
 *  说明：1) 操作共享数据的代码，即为需要被同步的代码。
 *       2) 共享数据：多个线程共同操作的变量。比如：ticket就是共享数据。
 *       3) 同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *          要求：多个线程必须要共用同一把锁。
 *          在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 *          在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。
 *
 *  方式二：同步方法。
 *  如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。
 *  1) 使用同步方法解决实现Runnable接口的线程安全问题
 *  2) 使用同步方法处理继承Thread类的方式中的线程安全问题
 *
 *  说明：1) 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 *       2) 非静态的同步方法，同步监视器是：this
 *          静态的同步方法，同步监视器是：当前类本身
 *
 */

/**
 * 方式一：同步代码块
 */
class WindowOne implements Runnable{
    private int ticket = 50;

    @Override
    public void run() {
        while(true){
            synchronized (this) { //方式一：此时的this就是唯一的WindowOne的对象
//            synchronized (WindowOne.class){ // 方式二：唯一的WindowOne的对象 {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("方式一：同步代码块  " + Thread.currentThread().getName() + ": sell ticket，ticket number: " + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}


/**
 * 方式二：同步方法 1) 使用同步方法解决实现Runnable接口的线程安全问题
 */
class WindowTwo extends Thread{
    private int ticket = 50;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private synchronized void show(){ //同步监视器：this
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("方式二：同步方法 1) " + Thread.currentThread().getName() + ": sell ticket，ticket number: " + ticket);
            ticket--;
        }
    }
}


/**
 * 方式二：同步方法 2) 使用同步方法处理继承Thread类的方式中的线程安全问题
 */
class WindowThree extends Thread{
    private static int ticket = 50;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private static synchronized void show(){//同步监视器：WindowThree.class
        //private synchronized void show(){ //同步监视器：t1,t2,t3。此种解决方式是错误的
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("方式二：同步方法 2)  " + Thread.currentThread().getName() + ": sell ticket，ticket number: " + ticket);
            ticket--;
        }
    }
}


public class SynchronizationTest {
    public static void main(String[] args) {
        // 方式一：同步代码块
        System.out.println("---------- 方式一：同步代码块 ----------");
        WindowOne w1 = new WindowOne();

        Thread t11 = new Thread(w1);
        Thread t12 = new Thread(w1);
        Thread t13 = new Thread(w1);

        t11.setName("window 1");
        t12.setName("window 2");
        t13.setName("window 3");

        t11.start();
        t12.start();
        t13.start();

        // 方式二：同步方法
        System.out.println("---------- 方式二：同步方法 1) 使用同步方法解决实现Runnable接口的线程安全问题 ----------");
        WindowTwo w2 = new WindowTwo();

        Thread t21 = new Thread(w2);
        Thread t22 = new Thread(w2);
        Thread t23 = new Thread(w2);

        t21.setName("window 1");
        t22.setName("window 2");
        t23.setName("window 3");

        t21.start();
        t22.start();
        t23.start();

        System.out.println("---------- 方式二：同步方法 2) 使用同步方法处理继承Thread类的方式中的线程安全问题 ----------");
        WindowThree t31 = new WindowThree();
        WindowThree t32 = new WindowThree();
        WindowThree t33 = new WindowThree();

        t31.setName("window 1");
        t32.setName("window 2");
        t33.setName("window 3");

        t31.start();
        t32.start();
        t33.start();
    }
}
