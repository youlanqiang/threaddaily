package top.youlanqiang.threadlean.juc.utils.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample3 {

    public static void main(String[] args) throws InterruptedException {
        //latch 默认不能为 负数，但可以为 0
        final CountDownLatch latch = new CountDownLatch(0);
        //如果为0 立即返回
        latch.await();
        System.out.println("====");


    }
}
