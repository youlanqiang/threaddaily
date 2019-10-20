package top.youlanqiang.threadlean.book.lesson8.training;

public interface MyRunnableQueue {

    /**
     * 提交任务队列
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 获取任务队列
     * @return
     */
    Runnable take() throws InterruptedException;

    /**
     * 任务队列的大小
     * @return
     */
    int size();

}
