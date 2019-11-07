package top.youlanqiang.threadlean.juc.utils.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample1 {

    public static void main(String[] args) {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, ()->{
            //在CyclicBarrier里面还可以写结束后的执行语句
            System.out.println("all done");
        });

        new Thread(){

            @Override
            public void run(){
                try{
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("T1 finished");
                    cyclicBarrier.await();
                    System.out.println("t1 other thread finished too");
                }catch(InterruptedException | BrokenBarrierException e){
                    e.printStackTrace();
                }
            }

        }.start();

        new Thread(){

            @Override
            public void run(){
                try{
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("T2 finished");
                    cyclicBarrier.await();
                    System.out.println("t2 other thread finished too");
                }catch(InterruptedException | BrokenBarrierException e){
                    e.printStackTrace();
                }
            }

        }.start();

        //执行结果
//        T2 finished
//        T1 finished
//        all done
//        t1 other thread finished too
//        t2 other thread finished too

        //api
        cyclicBarrier.getNumberWaiting(); //获取正在CyclicBarrier上等待的线程数量
        cyclicBarrier.getParties(); //获取CyclicBarrier打开屏障的线程数量

    }
}
