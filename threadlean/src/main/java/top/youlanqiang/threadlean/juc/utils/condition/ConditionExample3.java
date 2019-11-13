package top.youlanqiang.threadlean.juc.utils.condition;



import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample3 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition PRODUCE_COND = lock.newCondition();

    private final static Condition CONSUME_COND = lock.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            beginProduce(i);
        }

        for (int i = 0; i < 10; i++) {
            beginConsume(i);
        }

        for(;;){
            TimeUnit.SECONDS.sleep(5);
            System.out.println("==========");
            //lock.getWaitQueueLength 只能拿到锁的线程才能访问
           // System.out.println("consume--"+lock.getWaitQueueLength(CONSUME_COND));
        }
    }

    private static void beginProduce(int i){
        new Thread(()->{
            for(;;){
                produce();
                sleep(2);
            }
        },"P-"+i).start();
    }


    private static void beginConsume(int i){
        new Thread(()->{
            for(;;){
                consume();
                sleep(3);
            }
        },"C-"+i).start();
    }

    private static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void produce(){
        try{
            lock.lock();
            while(TIMESTAMP_POOL.size()>=MAX_CAPACITY){
                PRODUCE_COND.await();
            }
            System.out.println("produce--"+lock.getWaitQueueLength(PRODUCE_COND));
            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+"-P-"+ value);
            TIMESTAMP_POOL.addLast(value);
            CONSUME_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    private static void consume(){
        try{
            lock.lock();
            while(TIMESTAMP_POOL.isEmpty()){
                CONSUME_COND.await();
            }
            long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName()+"-C-"+ value);
            PRODUCE_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

}


