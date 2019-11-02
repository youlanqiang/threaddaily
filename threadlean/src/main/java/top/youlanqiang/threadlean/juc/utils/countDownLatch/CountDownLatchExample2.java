package top.youlanqiang.threadlean.juc.utils.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample2 {

    public static void main(String[] args) {

        /**
         * 2个线程，每个线程都在并行工作
         * 但是其中一个线程需要从另一个线程中拿到执行完毕的数据.
         * 使用 latch的解决方法.
         */

        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(){
            public void run(){
                System.out.println("Do some initial working.");

                try {
                    Thread.sleep(1000);
                    latch.await();
                    System.out.println("Do other working...");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread(){
            public void run(){
                System.out.println("asyn prepare for some data.");
                try {
                    Thread.sleep(2000);
                    System.out.println("data prepare for done.");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    latch.countDown();
                }
            }
        }.start();
    }
}
