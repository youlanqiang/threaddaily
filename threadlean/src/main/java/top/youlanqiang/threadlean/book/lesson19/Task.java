package top.youlanqiang.threadlean.book.lesson19;

@FunctionalInterface
public interface Task<IN, OUT> {

    //给定一个参数返回结果
    OUT get(IN input);


}
