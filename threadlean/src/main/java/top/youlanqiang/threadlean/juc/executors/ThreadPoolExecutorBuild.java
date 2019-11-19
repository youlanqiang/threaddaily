package top.youlanqiang.threadlean.juc.executors;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 如何构建一个ThreadPool
 */
public class ThreadPoolExecutorBuild {


    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) buildThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = 1;

        while(true){
            if(activeCount != executor.getActiveCount() || queueSize != executor.getQueue().size()) {
                System.out.println(executor.getActiveCount());//活跃的线程
                System.out.println(executor.getCorePoolSize());//核心线程数量
                System.out.println(executor.getQueue().size());//队列数量
                System.out.println(executor.getMaximumPoolSize());//最大线程数量
                activeCount = executor.getActiveCount();
                queueSize = executor.getQueue().size();
                System.out.println("=========");
            }
        }
    }


    private static ExecutorService buildThreadPoolExecutor(){
        //线程工厂
        //溺出策略
        ExecutorService executorService
                = new ThreadPoolExecutor(1,2,30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r ->{
                    Thread thread = new Thread(r);
                    return thread;
        }, new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The ThreadPoolExecutor create done.");
        return executorService;
    }



}
