package top.youlanqiang.threadlean.lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MyBooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    public void tryLock() throws InterruptedException {
        synchronized (this){
            if(locked){
                throw new InterruptedException("无法获取锁");
            }
            lock();
        }
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized(this){
            while(locked){
                blockedList.add(currentThread());
                this.wait();
            }
            this.blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized(this){
            if(mills <= 0){
                lock();
            }else {
                long remainingMills = mills;
                long endMills = mills + System.currentTimeMillis();
                while (locked) {
                    if(remainingMills <= 0){
                        throw new TimeoutException("cant not get the lock");
                    }
                    if(!blockedList.contains(currentThread())){
                        blockedList.add(currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
                this.blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized(this){
            if(currentThread == currentThread()){
                this.locked = false;
                System.out.println(currentThread + " release the lock");
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
