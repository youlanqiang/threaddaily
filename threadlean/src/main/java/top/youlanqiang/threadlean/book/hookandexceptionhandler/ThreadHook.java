package top.youlanqiang.threadlean.book.hookandexceptionhandler;

import java.util.concurrent.TimeUnit;

/**
 * 向Java程序注入Hook线程
 */
public class ThreadHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    try {
                        System.out.println("The hook thread 1 is running");
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("the hook thread 1 will exit.");
                })
        );

        //钩子线程可注册多个
        Runtime.getRuntime().addShutdownHook(new Thread(
                ()->{
                    try {
                        System.out.println("The hook thread 2 is running");
                        TimeUnit.SECONDS.sleep(1);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("The hook thread 2 will exit");
                }
        ));

        System.out.println("The program will is stopping.");

//        The program will is stopping.
//        The hook thread 2 is running
//        The hook thread 1 is running
//        the hook thread 1 will exit.
//        The hook thread 2 will exit
    }
}
