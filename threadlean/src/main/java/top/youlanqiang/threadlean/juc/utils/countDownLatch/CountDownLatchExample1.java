package top.youlanqiang.threadlean.juc.utils.countDownLatch;

import java.util.Random;
import java.util.concurrent.*;

public class CountDownLatchExample1 {


    private static Random random = new Random(System.currentTimeMillis());

    private static Executor executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        int[] data = query();

        for(int i = 0; i < data.length; i++){
            executor.execute(new SimpleRunnable(data, i, latch));
        }

        //使用CountDownLatch来等待任务全部完成
        latch.await();

        // ExecutorService 有shutdown方法，但也是异步的。
        ((ExecutorService)executor).shutdown();

        //阻塞式的等待任务完成
        //((ExecutorService) executor).awaitTermination(1, TimeUnit.SECONDS);


        System.out.println("work done!");

    }

    static class SimpleRunnable implements Runnable {

        private final int[] data;

        private final int index;

        private final CountDownLatch latch;

        public SimpleRunnable(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int value = data[index];
            if(value % 2 == 0){
                data[index] = value * 2;
            }else{
                data[index] = value * 10;
            }

            System.out.println(Thread.currentThread().getName() + " finished!");

            //计数减少
            latch.countDown();
        }
    }



    private static int[] query(){
        return new int[] {1,2,3,4,5,6,7,8,9,10};
    }
}
