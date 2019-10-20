package top.youlanqiang.threadlean.book.lesson19;

/**
 * 回调机制
 */
@FunctionalInterface
public interface Callable<T> {

    void call(T t);

}
