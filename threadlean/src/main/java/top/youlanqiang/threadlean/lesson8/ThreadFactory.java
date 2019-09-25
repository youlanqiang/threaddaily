package top.youlanqiang.threadlean.lesson8;

@FunctionalInterface
public interface ThreadFactory {

    //创建线程
    Thread createThread(Runnable runnable);

}
