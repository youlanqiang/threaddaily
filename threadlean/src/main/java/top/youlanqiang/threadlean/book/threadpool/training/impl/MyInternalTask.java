package top.youlanqiang.threadlean.book.threadpool.training.impl;

import top.youlanqiang.threadlean.book.threadpool.training.MyRunnableQueue;

public class MyInternalTask implements Runnable {

    private final MyRunnableQueue runnableQueue;

    private volatile boolean running = true;

    public MyInternalTask(MyRunnableQueue runnableQueue){
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while(running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = runnableQueue.take();
                task.run();
            }catch (InterruptedException e){
                this.running =false;
                return;
            }
        }
    }

    public void stop(){
        this.running = false;
    }
}
