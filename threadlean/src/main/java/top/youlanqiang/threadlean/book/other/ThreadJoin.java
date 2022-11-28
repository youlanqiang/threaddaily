package top.youlanqiang.threadlean.book.other;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author youlanqiang
 * created in 2022/1/21 1:55 下午
 * join方法
 * join某个线程A，会使当前线程B进入等待，直到线程A结束生命周期，或者到达给定的时间
 * 那么在此期间B线程是处于BLOCKED的。
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        // 定义2个线程，并保存在threads中
        List<Thread> threads = IntStream.range(1, 3)
                .mapToObj(ThreadJoin::create).collect(Collectors.toList());

        // 启动线程
        threads.forEach(Thread::start);

        // 执行这2个线程的join方法
        for (Thread thread : threads) {

            thread.join();
        }

        // main线程循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }

    }

    //创建一个简单的线程，线程只是做一些简单的循环输出
    private static Thread create(int seq){
        return new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(seq));
    }

    private static void shortSleep(){

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
