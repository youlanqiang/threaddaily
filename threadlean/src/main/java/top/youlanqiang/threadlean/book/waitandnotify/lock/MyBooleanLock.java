package top.youlanqiang.threadlean.book.waitandnotify.lock;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyBooleanLock implements Lock {


    public static void main(String[] args) throws InterruptedException {
        MyBooleanLock lock = new MyBooleanLock();
        Thread thread1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("hello");
                TimeUnit.SECONDS.sleep(10);
                lock.unlock();
            }catch(InterruptedException e){
                System.out.println("线程被interrupt了");
            }
        });

        Thread thread2 = new Thread(()->{
            try {
                lock.lock(1000);
            }catch (InterruptedException | TimeoutException e){
                e.printStackTrace();
            }
        });

        thread1.start();
        TimeUnit.MILLISECONDS.sleep(1);
        thread2.start();
        TimeUnit.SECONDS.sleep(5);
        thread1.interrupt();
    }


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
    public void lock(long millis) throws InterruptedException, TimeoutException {
            synchronized(this){
                if(millis < 0 ){
                    this.lock();
                }else{
                    Thread thread = Thread.currentThread();
                    long remainingMillis = millis;
                    long endMills = System.currentTimeMillis() + millis;
                    while(locked){
                        if(remainingMillis <= 0){
                            throw new TimeoutException();
                        }
                        if(!blockedList.contains(thread)){
                            blockedList.add(thread);
                        }
                        try {
                            this.wait(remainingMillis);
                        }catch(InterruptedException e){
                            blockedList.remove(thread);
                            throw e;
                        }

                        remainingMillis = endMills - System.currentTimeMillis();
                    }

                    this.locked = true;
                    this.currentThread = thread;
                    this.blockedList.remove(thread);
                }
            }
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
