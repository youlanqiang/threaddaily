package top.youlanqiang.threadlean.book.threadpool.train;



/**
 * @author youlanqiang
 * created in 2022/1/24 2:44 下午
 */
public class InternalTask implements Runnable{


    private final RunnableQueue queue;

    private volatile boolean running;




    public InternalTask(RunnableQueue queue){
        this.queue = queue;
        this.running = true;
    }


    @Override
    public void run() {
        while(running && !Thread.currentThread().isInterrupted()){
            try{
                Runnable runnable = queue.take();
                runnable.run();
            }catch(InterruptedException e){
                this.running = false;
            }
        }
    }

    public boolean isShutdown(){
        return !running;
    }

    public void stop(){
        this.running = false;
    }
}
