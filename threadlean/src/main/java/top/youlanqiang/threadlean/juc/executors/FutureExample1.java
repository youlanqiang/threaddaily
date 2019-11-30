package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.*;

/**
 * 在future中使用get可以设置超时设置
 * 超时结束后会抛异常，但是任务还是在执行直到结束。
 */
public class FutureExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       testGet();
    }

    private static void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //======
        System.out.println("=== i will be printed quickly.===");
        //======
        Integer result = future.get();
        System.out.println("===" + result + "===");
    }

}
