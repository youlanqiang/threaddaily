package top.youlanqiang.threadlean.book.threadpool;

@FunctionalInterface
public interface ThreadFactory {

    //创建线程
    Thread createThread(Runnable runnable);

}
