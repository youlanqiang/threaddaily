package top.youlanqiang.threadlean.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * API学习
 * acquire(number)
 * release(number)
 *
 * availablePermits() //剩余的许可证
 * getQueueLength()   //阻塞队列
 *
 * semaphore.acquireUninterruptibly()
 * semaphore.acquireUninterruptibly(number)
 */
public class SemaphoreExample3 {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread() {
            public void run() {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println("t2 finished");
            }
        };
        t1.start();
        TimeUnit.MILLISECONDS.sleep(1);

        Thread t2 = new Thread() {
            public void run() {
                try {
                    //semaphore.acquire();
                    //acquireUninterruptibly 可以无视掉 interrupt方法
                    semaphore.acquireUninterruptibly();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println("t2 finished");
            }
        };

        t2.start();

        TimeUnit.MILLISECONDS.sleep(1);
        t2.interrupt();
        /**
         * 程序被 acquire可以被interrupt
         */
    }
}
