package top.youlanqiang.threadlean.book.threadpool.training;

public interface MyThreadPool {

    // init <= core < max

    void execute(Runnable runnable);

    int getMaxSize();

    int getCoreSize();

    int getInitSize();

    int getActiveSize();

    int getQueueSize();

    void shutdown();

    boolean isShutdown();

}
