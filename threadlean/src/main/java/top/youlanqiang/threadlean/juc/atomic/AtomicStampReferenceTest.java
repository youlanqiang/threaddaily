package top.youlanqiang.threadlean.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 重新实现ABA问题
 * AtomicStampReference 解决 CAS 的 ABA 问题
 * CAS的ABA问题描述
 * 在CAS操作的时候，其他线程将当前变量的值从A改成B，又改回A；
 * CAS线程用期望值A与当前变量比较的时候，发现当前变量没有变，于是CAS就将当前变量进行了交换操作，但其实当前变量改变过，这与设计思想是不符合的；
 * ABA问题的解决思路：每次当前变量更新的时候，将当前变量的版本号加1；
 */
public class AtomicStampReferenceTest {

    private static AtomicInteger atomicInt = new AtomicInteger(100);

    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(100, 0);


    public static void main(String[] args) throws InterruptedException {
        Thread intABA = new Thread(()->{
            atomicInt.compareAndSet(100, 101);
            atomicInt.compareAndSet(101, 100);
        });

        Thread intCAS = new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){ }
            boolean isUpdated = atomicInt.compareAndSet(100, 101);
            System.out.println("Thread AtomicInteger CAS isUpdated: " + isUpdated); //true
        });

        intABA.start();
        intCAS.start();
        intABA.join();
        intCAS.join();


        Thread refABA = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {}
            atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
        });

        Thread refCAS = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp(); // 0
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
            boolean isUpdated = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println("Thread AtomicStampedReference CAS isUpdated: " + isUpdated); // false
        });

        refABA.start();
        refCAS.start();
    }



}
