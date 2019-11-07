package top.youlanqiang.threadlean.juc.utils.cyclicBarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CountDownLatch来模拟CyclicBarrier
 */
public class CyclicBarrierExample3 {

    static class MyCountDownLatch extends CountDownLatch{

        private final Runnable runnable;

        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        public void countDown(){
            super.countDown();
            if(getCount() == 0){
                this.runnable.run();
            }
        }
    }


    public static void main(String[] args) {
        final MyCountDownLatch myCountDownLatch =new MyCountDownLatch(2, ()->{
            System.out.println("all work finished done!");
        });

        new Thread(){
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " finished");
            }
        }.start();


        new Thread(){
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myCountDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " finished");
            }
        }.start();
    }
}
