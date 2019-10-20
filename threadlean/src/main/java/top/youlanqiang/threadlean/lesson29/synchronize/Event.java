package top.youlanqiang.threadlean.lesson29.synchronize;

/**
 * Event是对Message的一个基本实现
 */
public class Event implements Message{

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
