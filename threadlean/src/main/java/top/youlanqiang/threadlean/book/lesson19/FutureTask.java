package top.youlanqiang.threadlean.book.lesson19;


public class FutureTask<T> implements Future<T> {

    //计算结果
    private T result;

    //任务是否完成
    private boolean isDone = false;

    //定义对象锁
    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        //当任务还没有完成时，调用get方法会使被挂起而进入阻塞
        synchronized (LOCK){
            while(!isDone){
                LOCK.wait();
            }
        }
        return result;
    }

    //finish方法主要用于为futureTask设置计算结果
    protected void finish(T result){
        synchronized(LOCK){

            //balking设计模式
            if(isDone){
                return;
            }

            //计算完成，为result指定结果，并且将isDone设为true,
            // 同时唤醒阻塞中的线程
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
