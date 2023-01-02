package top.youlanqiang.threadlean.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * synchronized关键字并未提供一种获取Object Monitor失败的机制
 * 线程会一直阻塞，直到其他线程释放Object Monitor
 * 是哟AtomicBoolean可以实现可立即返回并且退出阻塞的显示锁Lock
 */
public class TryLock {

    public static void main(String[] args) {
        TryLock tryLock = new TryLock();
        if (tryLock.tryLock()) {
            System.out.println("try lock success.");
        }
    }

    private final AtomicBoolean ab = new AtomicBoolean(false);

    private final ThreadLocal<Boolean> threadLocal = ThreadLocal.withInitial(() -> false);

    public boolean tryLock() {
        boolean result = ab.compareAndSet(false, true);
        if (result) {
            threadLocal.set(true);
        }
        return result;
    }

    public boolean release() {
        // 判断release的线程是否成功的获得了该锁
        if (threadLocal.get()) {

            // 标记锁被释放
            threadLocal.set(false);
            return ab.compareAndSet(true, false);
        }

        return true;
    }
}
