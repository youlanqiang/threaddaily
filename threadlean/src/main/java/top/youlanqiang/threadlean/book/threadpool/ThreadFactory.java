package top.youlanqiang.threadlean.book.threadpool;

// 提供创建线程的接口
@FunctionalInterface
public interface ThreadFactory {

    //创建线程
    Thread createThread(Runnable runnable);

}
