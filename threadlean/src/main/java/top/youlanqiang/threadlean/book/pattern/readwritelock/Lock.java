package top.youlanqiang.threadlean.book.readwritelock;

public interface Lock {

    //获取显示锁，没有获取锁的线程将被阻塞
    void lock() throws InterruptedException;

    //释放获得的锁
    void unlock();

}
