package top.youlanqiang.threadlean.book.lesson8;

@FunctionalInterface
public interface ThreadFactory {

    //创建线程
    Thread createThread(Runnable runnable);

}
