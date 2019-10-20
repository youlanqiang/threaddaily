package top.youlanqiang.threadlean.book.lesson8;


/**
 * 线程池接口定义
 */
public interface ThreadPool {

    //提交任务到线程池
    void execute(Runnable runnable);

    //关闭线程池
    void shutdown();

    //获取线程池最大的线程数
    int getInitSize();

    //获取线程池的核心线程数量
    int getCoreSize();

    //获取线程池中用于缓存任务队列的大小
    int getQueueSize();

    //获取线程池中活跃线程的数量
    int getActiveCount();

    int getMaxSize();

    //查看线程池是否已经被shutdown
    boolean isShutdown();
}
