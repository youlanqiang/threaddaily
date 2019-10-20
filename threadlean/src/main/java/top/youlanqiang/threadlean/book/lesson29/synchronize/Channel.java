package top.youlanqiang.threadlean.book.lesson29.synchronize;

/**
 * Channel 主要用于接受来自EventLoop分配的消息
 */
public interface Channel<E extends Message> {

    /**
     * dispatch 方法用于负责Message的调度
     */
    void dispatch(E message);

}
