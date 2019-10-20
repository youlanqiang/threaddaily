package top.youlanqiang.threadlean.book.lesson7;

import java.util.concurrent.TimeUnit;

/**
 * 演示如何使用UncaughtExceptionHandler
 */
public class CaptureThreadException {


    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(
                (t, e)->{
                    System.out.println(t.getName() + " occur exception");
                    e.printStackTrace();
                }
        );

        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch(InterruptedException e){

            }
            // 这里会出现 unchecked异常
            System.out.println(1/0);
        }, "Test-Thread");

        thread.start();
    }
}
