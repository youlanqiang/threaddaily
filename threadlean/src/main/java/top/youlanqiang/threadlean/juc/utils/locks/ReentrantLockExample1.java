package top.youlanqiang.threadlean.juc.utils.locks;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReentrantLockExample1 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
//        IntStream.range(0, 2).forEach(i->
//                new Thread(ReentrantLockExample1::needLock).start());

        Thread thread1 = new Thread(()->testUnInterruptibly());
        thread1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(()->testUnInterruptibly());
        thread2.start();
        TimeUnit.SECONDS.sleep(1);

//        thread2.interrupt();
//        System.out.println("==============");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(lock.getQueueLength());
        System.out.println(lock.hasQueuedThreads());//是否有等待获取lock的线程

    }

    public static void testTryLock(){
        if(lock.tryLock()){
            try{
                Optional.of("The thread-"+Thread.currentThread().getName()+" get lock and will do working.").ifPresent(System.out::println);
                while(true){

                }
            }finally{
                lock.unlock();
            }
        }else{
            System.out.println("The thread-"+Thread.currentThread().getName()+ " not get lock");
        }
    }

    public static void testUnInterruptibly(){
        try{
            //一个可以被打断的锁
            lock.lockInterruptibly();
            Optional.of("The thread-"+Thread.currentThread().getName()+" get lock and will do working.").ifPresent(System.out::println);
            while(true){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    //使用 lock锁
    private static void needLock(){
        try{
            lock.lock();
            Optional.of("The thread-"+Thread.currentThread().getName()+" get lock and will do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    //使用 synchronized锁
    public static void needLockBySync()  {
        synchronized (ReentrantLockExample1.class){
            try {
                TimeUnit.SECONDS.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
