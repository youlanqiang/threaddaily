package top.youlanqiang.threadlean.book.threadpool.training;

@FunctionalInterface
public interface MyThreadFactory {

    /**
     * 创建一个线程
     * @param runnable
     * @return
     */
    Thread create(Runnable runnable);
}
