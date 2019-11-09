package top.youlanqiang.threadlean.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量,
 * Semaphore也是一个线程同步的辅助类，
 * 可以维护当前访问自身的线程个数，并提供了同步机制。
 * 使用Semaphore可以控制同时访问资源的线程个数，
 * 例如，实现一个文件允许的并发访问数。
 */
public class SemaphoreExample1 {

    public static void main(String[] args) {

        final SemaphoreLock lock = new SemaphoreLock();

        for(int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get the #semaphorelock");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " release the lock");
            }, i +"--t").start();
        }
    }

    static class SemaphoreLock{
        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException{
            //申请一个许可证对象
            semaphore.acquire();
        }


        public void unlock(){
            semaphore.release();
        }

    }


    private synchronized static void m(){

    }
}
