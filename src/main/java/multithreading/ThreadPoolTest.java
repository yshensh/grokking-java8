package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程的方式四：使用线程池
 *
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 *   corePoolSize：核心池的大小
 *   maximumPoolSize：最大线程数
 *   keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 */

class NumberThreadOne implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class NumberThreadTwo implements Runnable{
    @Override
    public void run() {
        for(int i = 0;i <= 100;i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPoolTest {
    public static void main(String[] args) {
        //1. 提供指定线程数量的线程池
        ExecutorService serviceOne = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor serviceTwo = (ThreadPoolExecutor) serviceOne;
        //设置线程池的属性
        System.out.println(serviceTwo.getClass());
        serviceTwo.setCorePoolSize(15);
        serviceTwo.setKeepAliveTime(15, TimeUnit.SECONDS);

        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        serviceTwo.execute(new NumberThreadOne()); //适用于Runnable
        serviceTwo.execute(new NumberThreadTwo());
//        serviceTwo.submit(Callable callable); //适用于Callable

        //3.关闭连接池
        serviceTwo.shutdown();
    }

}
