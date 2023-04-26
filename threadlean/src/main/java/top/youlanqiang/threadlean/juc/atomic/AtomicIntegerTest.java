package top.youlanqiang.threadlean.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    /**
     * 保证内存可见，保证有序性
     */
    private static volatile int value = 0;

    public static void main(String[] args) {

        /**
         * 内部实现 CAS算法 CompareAndSet
         */
        AtomicInteger at = new AtomicInteger();

        at.set(1);
        System.out.println(at.get());
        // +1
        at.getAndIncrement();
        System.out.println(at.getAndIncrement());
        // -1
        System.out.println(at.getAndDecrement());
    }

}
