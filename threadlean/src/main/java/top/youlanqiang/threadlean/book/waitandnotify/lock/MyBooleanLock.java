package top.youlanqiang.threadlean.book.waitandnotify.lock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MyBooleanLock implements Lock {

    private boolean locked;

    private Thread currentThread;

    private final List<Thread> blockedList;

    public MyBooleanLock(){
        this.locked = false;
        this.blockedList = new LinkedList<>();
    }


    @Override
    public void lock() throws InterruptedException {
        synchronized(this){
            Thread thread = Thread.currentThread();
            while(locked){
                if(!blockedList.contains(thread)){
                    blockedList.add(thread);
                }
                try {
                    this.wait();
                }catch(InterruptedException e){
                    blockedList.remove(thread);
                    throw e;
                }
            }

            blockedList.remove(thread);
            this.locked = true;
            this.currentThread = thread;
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {

    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread == Thread.currentThread()){
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return null;
    }
}
