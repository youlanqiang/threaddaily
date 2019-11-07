package top.youlanqiang.threadlean.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Exchanger用于进行线程间的数据交换。
 * 它提供一个同步点，在这个同步点两个线程可以交换彼此的数据。
 * 这两个线程通过exchange方法交换数据，
 * 如果第一个线程先执行exchange方法，
 * 它会一直等待第二个线程也执行exchange，
 * 当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。
 */
public class ExchangerExample1 {

    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();


        /**
         * 2个线程通过 exchange方法来交换数据
         * 注意exchange只适用于2个线程之间的交换
         * 不要使用超过2个线程之间的使用
         */

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                //设置时间超时异常
                String result = exchanger.exchange("I am come from T-A", 10, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + " get value [" + result + "]");
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        }, "==A==").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                String result = exchanger.exchange("I am come from T-B");
                System.out.println(Thread.currentThread().getName() + " get value [" + result + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "==B==").start();
    }
}
