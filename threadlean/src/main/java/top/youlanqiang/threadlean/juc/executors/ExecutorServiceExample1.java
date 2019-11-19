package top.youlanqiang.threadlean.juc.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ExecutorServiceExample1 {

    /**
     * ExecutorService api 介绍
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        //isShutDown();
        //isTerminated();
        //executeRunnableError();
        executeRunnableTask();
    }

    /**
     * 问题:
     * 当你调用shutdown了之后还能执行一个runnable吗?
     * 回答:
     * 不行，会报RejectedExecutionException
     */
    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        executorService.execute(() -> {
            System.out.println("i will be invoke?");
        });
    }

    /**
     * 是否终结.
     * ThreadPoolExecutor.isTerminating 是否正在终结
     */
    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());
    }

    private static void executeRunnableError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> System.out.println(1 / 0)));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("================");
    }


    private static class MyThreadFactory implements ThreadFactory {

        private final static AtomicInteger SEQ = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("The thread :" + t.getName());
                cause.printStackTrace();
                System.out.println("=========");
            });
            return thread;
        }
    }

    //推荐使用模板的设计模式来捕获Runnable中抛出的方法。
    private static void executeRunnableTask() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10, new MyThreadFactory());

        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(new MyTask(i) {
            @Override
            protected void doInit() {
                System.out.println("The no:" + i + " init");
            }

            @Override
            protected void doExecute() {
                System.out.println("The no:" + i + " execute");
            }

            @Override
            protected void done() {
                System.out.println("The no:" + i + " done");
            }

            @Override
            protected void error(Throwable cause) {
                System.out.println("The no:" + i + " fail");
            }
        }));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("================");
    }


    private abstract static class MyTask implements Runnable {

        private final int no;

        private MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected abstract  void doInit();

        protected abstract  void doExecute();

        protected abstract  void done();

        protected  abstract  void error(Throwable cause);
    }

}
