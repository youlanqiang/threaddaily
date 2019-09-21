package top.youlanqiang.threadlean.lesson5;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {

    void lock() throws InterruptedException; //线程上锁，类似synchronized

    //线程上锁添加超时时间
    void lock(long mills) throws InterruptedException, TimeoutException;

    //线程解锁
    void unlock();

    //获取当前所有被阻塞的线程
    List<Thread> getBlockedThreads();

}
