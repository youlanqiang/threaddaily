package top.youlanqiang.threadlean.juc.utils.semaphore;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample4 {

    public static void main(String[] args) throws InterruptedException {
        final MySemaphore semaphore = new MySemaphore(5);
        Thread t1 = new Thread(){
            public void run(){
                //排干了所有的许可证
                semaphore.drainPermits();
                System.out.println(semaphore.availablePermits());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release(5);
                }
                System.out.println("T1 finished");
            }
        };
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(){
            public void run(){
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release();
                }
                System.out.println("T2 finished");
            }
        };
        t2.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(semaphore.hasQueuedThreads());
        Collection<Thread> waitingThreads = semaphore.getWaitingThreads();
        for(Thread t: waitingThreads){
            System.out.println(t);
        }
    }

    static class MySemaphore extends Semaphore{

        public MySemaphore(int permits){
            super(permits);
        }

        /**
         * fair 是否公平
         * fair为true ，Semaphore会尽可能的保证每个线程都能获取一次
         * (如使用 tryAcquire)
         * 为false，semaphore不会对tryAcquire采取公平措施
         */
        public MySemaphore(int permits, boolean fair){
            super(permits, fair);
        }

        public Collection<Thread> getWaitingThreads(){
           return super.getQueuedThreads();
        }
    }

}
