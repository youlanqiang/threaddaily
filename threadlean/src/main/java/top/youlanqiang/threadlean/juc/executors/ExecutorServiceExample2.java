package top.youlanqiang.threadlean.juc.executors;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool的拒绝策略
 */
public class ExecutorServiceExample2 {

    public static void main(String[] args) throws InterruptedException {
        testCallerRunsPolicy();
    }


    /**
     * 线程池的拒绝策略
     * 抛出  java.util.concurrent.RejectedExecutionException
     * 因为最大工作线程2个，任务队列中有一个 只能容纳3个任务
     */
    private static void testAbortPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r->{
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 3; i++) {
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(()-> System.out.println("is abort .."));

    }

    /**
     * 超出任务队列的直接丢弃。
     * @throws InterruptedException
     */
    private static void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r->{
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 3; i++) {
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(()-> System.out.println("is abort .."));
        System.out.println("program done.");
    }

    /**
     * 超出任务队列的任务，直接在主线程执行。
     */
    private static void testCallerRunsPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r->{
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 3; i++) {
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        executorService.execute(()-> System.out.println("is abort .."));
        System.out.println("program done.");
    }

}
