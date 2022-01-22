package top.youlanqiang.threadlean.book.hookandexceptionhandler;

import java.util.concurrent.TimeUnit;

public class EmptyExceptionHandler {

    public static void main(String[] args) {

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());


        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException ignored){

            }
            System.out.println(1/0);
        });

        thread.start();
    }
}
