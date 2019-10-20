package top.youlanqiang.threadlean.book.lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();


    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while(locked){
                final Thread tempThread = currentThread();
                try{
                    if(!blockedList.contains(tempThread)){
                        blockedList.add(currentThread());
                    }
                    this.wait();
                }catch(InterruptedException e){
                    blockedList.remove(tempThread);
                    throw e;
                }

            }
            //表示当前线程被阻塞
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized(this){
            if(mills < 0){
                this.lock();
            }else{
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while(locked){
                    if(remainingMills <= 0){
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
                //表示当前线程被阻塞
                blockedList.remove(currentThread());
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
