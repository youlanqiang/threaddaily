package top.youlanqiang.threadlean.book.pattern.eventdriver.synchronize;

/**
 * Message是比Event更高一个层级的抽象
 * 每个Message都有一个特定的Type与对应的Handler做关联
 */
public interface Message {

    /**
     * 返回Message的类型
     */
    Class<? extends Message> getType();
}
