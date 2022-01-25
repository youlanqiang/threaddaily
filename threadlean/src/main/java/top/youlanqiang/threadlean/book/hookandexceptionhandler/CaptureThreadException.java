package top.youlanqiang.threadlean.book.hookandexceptionhandler;

import java.util.concurrent.TimeUnit;

/**
 * 演示如何使用UncaughtExceptionHandler
 */
public class CaptureThreadException {


    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(
                (t, e)->{
                    // t 是 Thread e 是 exception
                    System.out.println(t.getName() + " occur exception");
                    //e.printStackTrace();
                }
        );

        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch(InterruptedException ignored){

            }
            // 这里会出现 unchecked异常
            System.out.println(1/0);
        }, "Test-Thread");

        thread.start();
    }
}
