package top.youlanqiang.threadlean.book.threadpool;

/**
 * 这是Runnable的一个实现，主要用于线程池内部
 * 该类会使用到RunnableQueue，然后不断地从queue中取出某个runnable
 * 并运行runnable的run方法
 */
public class InternalTask  implements Runnable{


    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        // 当前任务为running，并且没有被interrupt，便不断的从queue里面获取runnable执行.
        while(running && !Thread.currentThread().isInterrupted()){
            try{
                Runnable task = runnableQueue.take();
                task.run();
            }catch(InterruptedException e){
                running = false;
                break;
            }
        }
    }

    // 可以用于停止线程
    public void stop(){
        this.running = false;
    }


}
