package top.youlanqiang.threadlean.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeTest {

    public static void main(String[] args) throws Exception {
        //Unsafe是无法直接得到的,但是可以通过使用反射得到
        //Unsafe unsafe = Unsafe.getUnsafe();//java.lang.SecurityException: Unsafe


        ExecutorService service = Executors.newFixedThreadPool(100);
        //Counter counter = new StupiedCounter();
        //Counter counter = new SyncCounter();
        //Counter counter = new LockCounter();
        //Counter counter = new AtomicCounter();
        Counter counter = new CasCounter();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            service.submit(new CounterRunnable(counter, 10000));
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();

        System.out.println("Counter result:" + counter.getCounter());
        System.out.println("Time passed in ms:" + (end - start));

        //used by stupiedCounter
        //Counter result:7837647
        //Time passed in ms:22

        //used by SyncCounter
        //Counter result:10000000
        //Time passed in ms:385

        //used by LockCounter
        //Counter result:10000000
        //Time passed in ms:222

        //used by AtomicCounter
        //Counter result:10000000
        //Time passed in ms:387

        //used by CasCounter
        //Counter result:10000000
        //Time passed in ms:891



        // 无锁 > Lock > 同步 > 原子
    }

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
        unsafe.setAccessible(true);
        //theUnsafe是静态常量
        return (Unsafe) unsafe.get(null);
    }

    interface Counter{
        void increment();
        long getCounter();
    }

    static class StupiedCounter implements Counter{

        private long counter = 0;
        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class SyncCounter implements Counter{

        private long counter = 0;
        @Override
        public synchronized void   increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter{

        private long counter = 0;

        private final Lock lock = new ReentrantLock();

        @Override
        public  void   increment() {
            try {
                lock.lock();
                counter++;
            }finally{
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter{

        private final AtomicLong counter = new AtomicLong(0);
        @Override
        public synchronized void   increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    static class CasCounter implements Counter{

        private volatile long counter = 0;

        private Unsafe unsafe;

        private long offset;

        CasCounter() throws Exception {
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }

        @Override
        public  void   increment() {

            long current = counter;
            while(!unsafe.compareAndSwapLong(this, offset, current, current+1)){
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }


    static class CounterRunnable implements Runnable{
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for(int i = 0; i < num; i++){
                counter.increment();
            }
        }
    }
}
