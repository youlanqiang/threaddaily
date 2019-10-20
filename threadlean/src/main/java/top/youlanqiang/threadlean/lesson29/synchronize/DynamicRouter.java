package top.youlanqiang.threadlean.lesson29.synchronize;

/**
 * 作用类似于EventLoop,为每种message分配对应的channel
 * @param <E>
 */
public interface DynamicRouter<E extends Message> {


    /**
     * 针对每一种message注册对应的Channel 只有对应的channel才会处理对应的message
     */
    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    /**
     * 为相应的Channel分配message
     * @param message
     */
    void dispatch(E message);

}
