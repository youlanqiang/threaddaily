package top.youlanqiang.threadlean.juc.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ExecutorsExample2 {

    public static void main(String[] args) throws InterruptedException {
        //基于ForkJoinPool实现的Pool
        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i->
            (Callable<String>) ()->{
                System.out.println("Thread " + Thread.currentThread());
                sleep(2);
                return "Task-" +i;
            }
        ).collect(Collectors.toList());

        executorService.invokeAll(callableList).stream().map(x->{
            try {
                return x.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).forEach(System.out::println);
    }

    private static void sleep(long seconds) {
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
