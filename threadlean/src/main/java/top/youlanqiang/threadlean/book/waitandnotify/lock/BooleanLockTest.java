package top.youlanqiang.threadlean.book.waitandnotify.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BooleanLockTest {

    private static final Lock lock = new BooleanLock();

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest test = new BooleanLockTest();

        // 多个线程通过lock方法来争抢锁
//        IntStream.range(0, 10).mapToObj(i -> new Thread(test::syncMethod))
//                .forEach(Thread::start);

        // 可中断被阻塞的线程
        new Thread(test::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(test::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t2.interrupt(); // t2因为争抢同一个lock而被阻塞，但是可以使用 interrupt方法打断







    }


    public void syncMethod() {
        try{
            lock.lock();

            int randomInt = new Random().nextInt(10);
            System.out.println(Thread.currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
