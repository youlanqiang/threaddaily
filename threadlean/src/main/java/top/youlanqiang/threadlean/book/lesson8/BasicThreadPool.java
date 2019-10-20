package top.youlanqiang.threadlean.book.lesson8;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;


/**
 * 线程池需要有数量控制属性，创建线程工厂，任务队列策略等功能
 */
public class BasicThreadPool extends Thread implements ThreadPool{

    //初始化线程数量
    private final int initSize;

    //线程池最大线程数量
    private final int maxSize;

    //线程池核心线程数量
    private final int coreSize;

    //当前活跃的线程数量
    private int activeCount;

    //创建线程所需要的工厂
    private final ThreadFactory threadFactory;

    //任务队列
    private final RunnableQueue runnableQueue;

    //线程池是否已经被shutdown
    private volatile boolean isShutdown = false;

    //工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY
            = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY
            = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;


    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize){
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           ThreadFactory threadFactory, int queueSize, DenyPolicy denyPolicy,
                           long keepAliveTime, TimeUnit timeUnit){
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init(){
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }


    /**
     * 提交任务只需要将runnable提交到queue
     * @param runnable
     */
    @Override
    public void execute(Runnable runnable) {
        if(this.isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        this.runnableQueue.offer(runnable);
    }

    /**
     * run实现线程池的自动维护
     * 主要用于维护线程数量，比如扩容，回收等工作
     */
    @Override
    public void run() {
        while(!isShutdown && !isInterrupted()){
            try {
                timeUnit.sleep(keepAliveTime);
            }catch(InterruptedException e){
                isShutdown = true;
                break;
            }
            synchronized(this){
                if(isShutdown){
                    break;
                }

                //当前的队列中有任务尚未处理，并且activeCount < coreSize则继续扩容
                if(runnableQueue.size() > 0 && activeCount < coreSize){
                    for (int i = initSize; i < coreSize; i++){
                        newThread();
                    }
                    //不想让线程的扩容直接达到maxsize
                    continue;
                }

                //当前的队列中有任务尚未处理，并且activeCount < maxSize则继续扩容
                if(runnableQueue.size() > 0 && activeCount < maxSize){
                    for (int i = initSize; i < maxSize; i++){
                        newThread();
                    }
                }

                //如果当前线程中没有任务，便需要回收，则回收到coreSize即可
                if(runnableQueue.size() == 0 && activeCount > coreSize){
                    for(int i = coreSize; i < activeCount; i++){
                        removeThread();
                    }
                }

            }
        }
    }

    /**
     * 停止BasicThreadPool线程
     * 停止线程池中的活动线程并且将isShutdown改为true
     */
    @Override
    public void shutdown() {
        synchronized(this){
            if(isShutdown){
                return;
            }
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if(isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.initSize;
    }

    @Override
    public int getCoreSize() {
        if(isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if(isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return runnableQueue.size();
    }

    @Override
    public int getMaxSize() {
        if(isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.maxSize;
    }

    @Override
    public int getActiveCount() {
        synchronized (this){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private void newThread(){
        //创建任务队列，并且启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start(); 
    }

    private void removeThread(){
        //从线程池中移除某个线程
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    //threadtask 是thread和internaltask的一个组合
    private static class ThreadTask{
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask){
            this.thread = thread;
            this.internalTask = internalTask;
        }

    }

}
