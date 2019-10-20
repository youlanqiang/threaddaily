package top.youlanqiang.threadlean.book.lesson8;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        //定义线程池，初始化线程池数量为2，核心数量为4，最大线程数量为6，任务队列最多有1000个任务
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

        //定义20个任务队列并且提交给线程池
        for(int i = 0; i < 20; i++){
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for(;;){
            System.out.println("getActiveCount:" + threadPool.getActiveCount());
            System.out.println("getQueueSize:" + threadPool.getQueueSize());
            System.out.println("getCoreSize:" + threadPool.getCoreSize());
            System.out.println("getMaxSize:" + threadPool.getMaxSize());
            System.out.println("===================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
