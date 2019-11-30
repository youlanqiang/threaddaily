package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.*;

public class FutureExample2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        testIsDone();
    }

    private static void testIsDone() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        /**
         * normal exception cancellation
         * 3种结果 isDone == true
         */
        System.out.println(future.isDone());
    }
}
