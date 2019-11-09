package top.youlanqiang.threadlean.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample2 {

    public static void main(String[] args) throws InterruptedException {
        //semaphore申请2个许可证
        final Semaphore semaphore = new Semaphore(2);
        for(int i = 0; i < 2; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " in");

                try {
                    semaphore.acquire(1); //申请许可证
                    System.out.println(Thread.currentThread().getName() + " get the semaphore");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release(1);//释放许可证
                }


                System.out.println(Thread.currentThread().getName() + " out");
            }).start();

        }

        while(true){
            //availablePermits 当前可用的许可证
            System.out.println("AP->" + semaphore.availablePermits());
            System.out.println("QL->" + semaphore.getQueueLength());
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
