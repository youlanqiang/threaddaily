package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorServiceExample3 {


    public static void main(String[] args) throws InterruptedException {
        testPrestartCoreThread();
    }

    /**
     * 每提交一个任务 active会激活一个线程
     */
    private static void test() throws InterruptedException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(pool.getActiveCount());
        pool.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(pool.getActiveCount());//1
    }

    private static void testAllowCoreThreadTimeOut() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        //允许 core thread在超时后销毁.
        pool.setKeepAliveTime(10, TimeUnit.SECONDS);
        pool.allowCoreThreadTimeOut(true);
        IntStream.range(0, 5).boxed().forEach(i->{
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private static void testRemove() throws InterruptedException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        //允许 core thread在超时后销毁.
        pool.setKeepAliveTime(10, TimeUnit.SECONDS);
        pool.allowCoreThreadTimeOut(true);
        IntStream.range(0, 2).boxed().forEach(i->{
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("==== i finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        TimeUnit.MILLISECONDS.sleep(20);
        Runnable r = ()->{
            System.out.println("I will never be executed!");
        };
        pool.execute(r);
        TimeUnit.SECONDS.sleep(20);
        //ExecutorService 可以删除掉在任务队列（Queue）中的runnable
        pool.remove(r);

    }

    private static void testPrestartCoreThread(){
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(pool.getActiveCount());
        /**
         * 开启一个核心线程
         */
        pool.prestartCoreThread();
        System.out.println(pool.getActiveCount());
        pool.prestartCoreThread();
        System.out.println(pool.getActiveCount());
    }

}
