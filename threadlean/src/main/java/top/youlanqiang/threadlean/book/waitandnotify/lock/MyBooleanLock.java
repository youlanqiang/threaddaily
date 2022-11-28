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
        
    }

    @Override
    public void lock(long millis) throws InterruptedException, TimeoutException {

    }

    @Override
    public void unlock() {

    }

    @Override
    public List<Thread> getBlockedThreads() {
        return null;
    }
}
