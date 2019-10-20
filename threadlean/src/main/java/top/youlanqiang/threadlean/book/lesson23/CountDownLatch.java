package top.youlanqiang.threadlean.book.lesson23;

import java.util.concurrent.TimeUnit;

public class CountDownLatch extends Latch {

    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            while (limit > 0) {
                this.wait();
            }
        }
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException {
        if(time <= 0){
            throw new IllegalArgumentException("The time is invalid");
        }
        long remainingNanos = unit.toNanos(time);
        final long endNanos = System.nanoTime() + remainingNanos;
        synchronized (this){
            while(limit > 0){
                if(TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0){
                    throw new RuntimeException("The wait time over specify time");
                }
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos - System.nanoTime();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if(limit <= 0){
                throw new IllegalStateException("all of task already arrived");
            }
            //使limit减一，并且通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        //返回有多少线程还未完成任务
        return limit;
    }
}
