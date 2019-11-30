package top.youlanqiang.threadlean.juc.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureExample1 {

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        Future<?> future = executorService.submit(()->{
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        while(!future.isDone()){
//
//        }
//        System.out.println("DONE");

        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v,t)-> System.out.println("DONE"));
        System.out.println("==== i am not blocked ====");
        Thread.currentThread().join();


    }


}
