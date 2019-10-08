package top.youlanqiang.threadlean.lesson8.training;

@FunctionalInterface
public interface MyThreadFactory {

    /**
     * 创建一个线程
     * @param runnable
     * @return
     */
    Thread create(Runnable runnable);
}
