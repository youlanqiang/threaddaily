package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorsExample1 {

    public static void main(String[] args) throws InterruptedException {
        useFixedSizePool();
    }

    private static void useSinglePool(){
        //只有一个核心工作线程的线程池，让任务按照顺序执行
        //等价于Executors.newFixedThreadPool(1)
        ThreadPoolExecutor singleExecutor =  (ThreadPoolExecutor)Executors.newSingleThreadExecutor();


    }


    private static void useFixedSizePool() throws InterruptedException {
        //一个可重用的固定线程数的线程池
        ThreadPoolExecutor fixedExecutor =  (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
        System.out.println(fixedExecutor.getActiveCount());

        fixedExecutor.execute(()-> System.out.println("======="));
        System.out.println(fixedExecutor.getActiveCount());

        IntStream.range(0, 20).boxed().forEach(i-> fixedExecutor.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(fixedExecutor.getActiveCount());
    }


    private static void useCachedThreadPool() throws InterruptedException {
        //无上线线程池
        //内部使用SynchronousQueue
        //因为maximumPoolSize是无界的
        //所以提交任务的速度 > 线程池中线程处理任务的速度就要不断创建新线程；
        //每次提交任务，都会立即有线程去处理，因此CachedThreadPool适用于处理大量、耗时少的任务。
        ThreadPoolExecutor cachedExecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
        System.out.println(cachedExecutor.getActiveCount());

        cachedExecutor.execute(()-> System.out.println("======="));
        System.out.println(cachedExecutor.getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i-> cachedExecutor.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(cachedExecutor.getActiveCount());
    }

}
