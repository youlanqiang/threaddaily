package top.youlanqiang.threadlean.book.lesson8;

import java.util.LinkedList;

public class LinkedRunnableQueue implements RunnableQueue {

    //任务队列的最大容量， 在构造时传入
    private final int limit;

    //若任务队列中的任务已经满了，则需要执行拒绝策略
    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool){
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized(runnableList){
            if(runnableList.size() >= limit){
                //无法加入队列时，执行拒绝策略
                denyPolicy.reject(runnable, threadPool);
            }else{
                //将任务加入到队尾，并且唤醒阻塞中的线程
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException{

        synchronized(runnableList){
            while(runnableList.isEmpty()){
                try{
                    runnableList.wait();
                }catch(InterruptedException e){
                    //被中断时需要将该异常抛出
                    throw e;
                }
            }
            //从任务队列头部移除一个任务
            return runnableList.removeFirst();
        }

    }

    @Override
    public int size() {
        synchronized (runnableList){
            return runnableList.size();
        }
    }
}
