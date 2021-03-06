package top.youlanqiang.threadlean.book.threadgroup;

import java.util.concurrent.TimeUnit;

public class ThreadGroupTest {

    public static void main(String[] args) throws InterruptedException{
        ThreadGroup myGroup = new ThreadGroup("MyGroup");
        Thread thread = new Thread(myGroup, ()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){

                }
            }
        }, "MyThread");
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];
        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);

        //recurse 递归
        recurseSize = mainGroup.enumerate(list, false);
        System.out.println(recurseSize);


    }
}
