package top.youlanqiang.threadlean.lesson27;

import top.youlanqiang.threadlean.lesson19.FutureTask;


/**
 * ActiveFuture是FutureTask的直接子类，其主要作用是重写finish方法,
 * 并且将protected的权限换成public， 可以使得执行线程完成任务之后传递最终结果
 * @param <T>
 */
public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    public void finish(T result){
        super.finish(result);
    }

}
