package top.youlanqiang.threadlean.juc.utils.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample2 {

    public static void main(String[] args) throws Exception{

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(){
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(3);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }.start();

        new Thread(){
            public void run(){
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }.start();

        TimeUnit.MICROSECONDS.sleep(100);
        cyclicBarrier.reset();
        /**
         * reset方法会打断所有在等待的线程，调用 cyclicBarrier.await方法
         * 并且会抛出 BrokenBarrierException异常，之后会重置cyclicBarrier
         */

    }
}
