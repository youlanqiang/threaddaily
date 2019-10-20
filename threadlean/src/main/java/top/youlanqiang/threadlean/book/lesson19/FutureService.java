package top.youlanqiang.threadlean.book.lesson19;

public interface FutureService<IN, OUT> {

    //提交不需要返回值的任务，Future,get方法返回的是null
    Future<?> submit(Runnable runnable);

    //提交一个带返回值的任务，其中Task代替了Runnable接口
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    Future<OUT> submit(Task<IN, OUT> task, IN input, Callable<OUT> callable);

    static <T, R> FutureService<T, R> newService(){
        return new FutureServiceImpl<>();
    }
}

