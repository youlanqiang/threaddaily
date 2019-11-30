package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * java定时任务
         * 在任务结束后才会执行下一次定时任务
         */
        ScheduledThreadPoolExecutor executor
                = new ScheduledThreadPoolExecutor(2);
//        ScheduledFuture<?> future = executor.schedule(()-> System.out.println("===i will be invoked!"), 2, TimeUnit.SECONDS);
//        System.out.println(future.cancel(true));
//        ScheduledFuture<Integer> future = executor.schedule(()->2,2, TimeUnit.SECONDS);
//        System.out.println(future.get());

        ScheduledFuture<?> future1 = executor.scheduleAtFixedRate(()-> System.out.println("i am running "+System.currentTimeMillis()), 1,2, TimeUnit.SECONDS);
    }
}
