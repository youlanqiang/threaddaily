package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTask {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService
                = new ThreadPoolExecutor(10,20,30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r ->{
            Thread thread = new Thread(r);
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 20).boxed().forEach(i->{
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName()+" ["+i+"] finish done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        //关闭线程池的方法，会等待线程全部执行完毕后关闭
        //executorService.shutdown();
        //等待一个小时后关闭线程
        executorService.awaitTermination(1, TimeUnit.HOURS);
        //立刻关闭线程,返回未开始执行的任务队列
        executorService.shutdownNow();

    }
}
