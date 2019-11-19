package top.youlanqiang.threadlean.juc.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorServiceExample4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        testSubmitRunnable();
    }

    /**
     * invokeAny的用法
     */
    private static void testInvokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> list = IntStream.range(0, 5).boxed().map(i->(Callable<Integer>) ()->{
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            return i;
        }).collect(Collectors.toList());
        /**
         * invokeAny是同步方法
         */
        Integer value = executorService.invokeAny(list);
        System.out.println("====finished====");
        System.out.println(value);
    }


    private static void testInvokeAnyTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> list = IntStream.range(0, 5).boxed().map(i->(Callable<Integer>) ()->{
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            return i;
        }).collect(Collectors.toList());
        /**
         * invokeAny是同步方法
         */
        Integer value = executorService.invokeAny(list, 3, TimeUnit.SECONDS);
        System.out.println("====finished====");
        System.out.println(value);
    }

    private static void testInvokeAll() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> list = IntStream.range(0, 5).boxed().map(i->(Callable<Integer>) ()->{
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            return i;
        }).collect(Collectors.toList());
        /**
         * invokeAll是同步方法
         */
        List<Future<Integer>> value = executorService.invokeAll(list);
        System.out.println("====finished====");
    }


    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Integer> future = executorService.submit(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        Object get = future.get();
        System.out.println("value:" + get);
    }
}
