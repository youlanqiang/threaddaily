package top.youlanqiang.threadlean.book.pattern.future;

/**
 * 回调机制
 */
@FunctionalInterface
public interface Callable<T> {

    void call(T t);

}
