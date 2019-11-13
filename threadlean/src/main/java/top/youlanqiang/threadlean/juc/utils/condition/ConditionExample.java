package top.youlanqiang.threadlean.juc.utils.condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition是在java 1.5中才出现的，
 * 它用来替代传统的Object的wait()、notify()实现线程间的协作，
 * 相比使用Object的wait()、notify()，
 * 使用Condition的await()、signal()这种方式实现线程间协作更加安全和高效。
 * 因此通常来说比较推荐使用Condition，阻塞队列实际上是使用了Condition来模拟线程间协作。
 * 1. condition依赖于Lock接口，生成一个condition的基本代码为lock.newCondition()
 * 2. 调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 * （同 Object 只能在Synchronized中使用 wait和notify一样）
 */
public class ConditionExample {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    private static void buildData(){
        try{
            lock.lock();  //synchronized key word
            while(noUse){
                condition.await(); //monitor.wait()
            }
            data++;
            Optional.of("P:" + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            //使用signal会自动放弃CPU执行权
            condition.signalAll(); //monitor.notify()
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //synchronized end
        }
    }

    private static void useData(){
        try{
            lock.lock();
            while(!noUse){
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
            noUse = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            for(;;){
                buildData();
            }
        }).start();

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for(;;){
                    useData();
                }
            }).start();
        }


    }

}
