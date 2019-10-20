package top.youlanqiang.threadlean.book.lesson27;

import java.util.LinkedList;

/**
 * ActiveMessageQueue对应于 Worker-Thread模式中的传送带
 * 主要用于传送调用线程通过Proxy提交过来的MethodMessage，
 * 但是这个传送带允许存放无限的MethodMessage（没有limit约束，理论上可以存放无限多个MethodMessage，直到发生堆内存溺出的异常）
 */
public class ActiveMessageQueue {

    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue(){
        new ActiveDaemonThread(this).start();
    }


    public void offer(MethodMessage methodMessage){
        synchronized(this){
            messages.addLast(methodMessage);
            this.notify();
        }
    }

    protected MethodMessage take(){
        synchronized(this){
            //当MethodMessage队列中没有Message的时候,执行线程进入阻塞
            while(messages.isEmpty()){
                try{
                    this.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            //获取其中一个MethodMessage并且从队列中移除
            return messages.removeFirst();
        }
    }


    /**
     * ActiveDaemonThread是一个守护线程
     * 主要是从queue中获取Message然后执行execute方法
     */
    public static class ActiveDaemonThread extends Thread{

        private final ActiveMessageQueue queue;

        public ActiveDaemonThread(ActiveMessageQueue queue){
            super("ActiveDaemonThread");
            this.queue = queue;
            //ActiveDaemonThread 为守护线程
            setDaemon(true);
        }

        @Override
        public void run() {

            for(;;){
                /**
                 * 从 MethodMessage 队列中获取一个MethodMessage，然后执行execute方法
                 */
                MethodMessage methodMessage = this.queue.take();
                methodMessage.execute();

            }
        }
    }

}
