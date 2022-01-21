package top.youlanqiang.threadlean.book.waitandnotify.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BooleanLockTest {

    private static final Lock lock = new BooleanLock();

    public static void main(String[] args) {
        BooleanLockTest test = new BooleanLockTest();

        IntStream.range(0, 10).mapToObj(i -> new Thread(test::syncMethod))
                .forEach(Thread::start);

    }


    public void syncMethod() {
        try{
            lock.lock();

            int randomInt = new Random().nextInt(10);
            System.out.println(Thread.currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
