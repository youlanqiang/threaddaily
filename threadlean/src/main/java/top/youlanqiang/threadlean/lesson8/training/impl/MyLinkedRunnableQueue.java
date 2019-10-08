package top.youlanqiang.threadlean.lesson8.training.impl;

import top.youlanqiang.threadlean.lesson8.training.MyDenyPolicy;
import top.youlanqiang.threadlean.lesson8.training.MyRunnableQueue;
import top.youlanqiang.threadlean.lesson8.training.MyThreadPool;

import java.util.LinkedList;

public class MyLinkedRunnableQueue implements MyRunnableQueue {

    private final int limit;

    private final LinkedList<Runnable> runnables;

    private final MyThreadPool threadPool;

    private final MyDenyPolicy denyPolicy;

    public MyLinkedRunnableQueue(int limit, MyThreadPool threadPool, MyDenyPolicy denyPolicy){
        this.limit = limit;
        this.threadPool = threadPool;
        this.denyPolicy = denyPolicy;
        this.runnables = new LinkedList<>();
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized(runnables){
            if(runnables.size() >= limit){
                denyPolicy.reject(runnable, threadPool);
            }else{
                runnables.addLast(runnable);
                runnables.notifyAll();
            }
        }

    }

    @Override
    public Runnable take() throws InterruptedException{
        synchronized(runnables){
            while(runnables.isEmpty()){
                try {
                    runnables.wait();
                }catch(InterruptedException e){
                   throw e;
                }
            }
            return runnables.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized(runnables){
            return runnables.size();
        }
    }
}
