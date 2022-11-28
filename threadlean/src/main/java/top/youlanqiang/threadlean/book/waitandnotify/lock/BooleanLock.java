package top.youlanqiang.threadlean.book.waitandnotify.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {

    private Thread currentThread; // 当前获取到该lock的线程

    private boolean locked = false; // true表示以被某个线程获取到，false表示为空闲状态

    private final List<Thread> blockedList = new ArrayList<>(); //存储哪些线程在获取lock时进入阻塞状态


    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while(locked){
                final Thread tempThread = currentThread();
                try{
                    if(!blockedList.contains(tempThread)){
                        blockedList.add(currentThread());
                    }

                    // 如果当前锁已经被某个线程获取，则该线程加入阻塞队列并且使当前线程wait
                    // 释放对 this monitor的所有权
                    this.wait();
                }catch(InterruptedException e){
                    //如果线程被interrupt打断，则从blockedList中删除
                    blockedList.remove(tempThread);
                    throw e;
                }

            }
            blockedList.remove(currentThread());
            //尝试从blockedList中删除，如果blockedList中没有，则没有任何影响。
            //如果当前线程是被打断的，从wait set中被唤醒的，则需要从阻塞队列中将自己删除。



            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized(this){
            if(mills < 0){ // mills如果不合法则默认调用lock方法。
                this.lock();
            }else{
                long remainingMills = mills; // remainingMills是剩余等待时间，会在while循环中重复计算直到少于0
                long endMills = System.currentTimeMillis() + remainingMills; //结束的时间轴
                while(locked){
                    if(remainingMills <= 0){ //剩余时间小于等于0直接抛出timeout异常
                        throw new TimeoutException("can not get the lock during " + mills);
                    }
                    final Thread tempThread = currentThread();
                    try {
                        if (!blockedList.contains(currentThread())) {
                            blockedList.add(currentThread());
                        }
                        this.wait(remainingMills);
                    }catch (InterruptedException e){
                        this.blockedList.remove(tempThread);
                        throw e;
                    }
                    remainingMills = endMills - System.currentTimeMillis();
                }


                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized(this){
            //将locked改为false，并唤醒 wait set中的所有线程重新争抢锁。
            if(currentThread == currentThread()){ // 只有加了锁的线程才有资格解锁
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return this.blockedList;
    }

    private static Thread currentThread(){
        return Thread.currentThread();
    }
}
