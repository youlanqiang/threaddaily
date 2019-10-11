package top.youlanqiang.threadlean.lesson23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 线程员旅行线程
 */
public class ProgrammerTravel extends Thread {

    //门阀
    private final Latch latch;

    //程序员
    private final String programmer;

    //交通工具
    private final String transportation;

    //通过构造函数传入latch  programmer  transportation
    public ProgrammerTravel(Latch latch, String programmer, String transportation){
        this.latch = latch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation [" + transportation + "]");
        try{
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by " + transportation);
        latch.countDown();
    }


}
