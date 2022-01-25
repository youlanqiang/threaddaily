package top.youlanqiang.threadlean.book.threadpool.train;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author youlanqiang
 * created in 2022/1/24 2:20 下午
 * 测试自定义一个线程池
 */
public class MyThreadPool extends Thread{

    private int initSize;

    private int coreSize;

    private int maxSize;

    private int activeSize;

    private final AtomicInteger count = new AtomicInteger(0);

    private ThreadGroup threadGroup;

    private final List<ThreadTask> taskList; // 工作线程

    private final RunnableQueue list; // 任务队列

    public MyThreadPool(){
        this.initSize = 2;
        this.coreSize = 5;
        this.maxSize = 10;
        this.list = new RunnableQueue(10, new DenyPolicy.AbortDenyPolicy());
        this.threadGroup = new ThreadGroup("my-thread-pool");
        this.taskList = new ArrayList<>(maxSize);

        for (int i = 0; i < initSize; i++) {
            createThread();
        }

        this.start();
    }


    @Override
    public void run(){

    }

    // 创建线程
    private void createThread(){
        InternalTask task = new InternalTask(this.list);
        Thread thread = new Thread(threadGroup, task,
                "my-task-"+count.getAndIncrement());
        ThreadTask threadTask = new ThreadTask(thread, task);
        taskList.add(threadTask);
        activeSize++;
        thread.start();
    }

    // 销毁线程
    private void removeThread(){

    }

    // threadTask是 InternalTask和Thread的一个组合类
    // 线程池可以方便的使用 InternalTask来结束线程
    class ThreadTask{

        Thread thread;

        InternalTask task;

        public ThreadTask(Thread thread, InternalTask task){
            this.thread = thread;
            this.task = task;
        }

    }
}
