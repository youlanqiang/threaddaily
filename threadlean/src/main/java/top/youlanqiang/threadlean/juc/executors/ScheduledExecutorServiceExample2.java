package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample2 {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        executor.scheduleWithFixedDelay(()-> System.out.println("===" +System.currentTimeMillis())
        ,1, 2, TimeUnit.SECONDS);

    }
}
