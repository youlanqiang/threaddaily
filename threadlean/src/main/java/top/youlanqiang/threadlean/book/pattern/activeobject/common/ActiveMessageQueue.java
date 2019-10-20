package top.youlanqiang.threadlean.book.pattern.activeobject.common;


import java.util.LinkedList;

public class ActiveMessageQueue {



    private final LinkedList<ActiveMessage> messages = new LinkedList<>();

    public ActiveMessageQueue(){
        new ActiveDaemonThread(this).start();
    }

    public void offer(ActiveMessage activeMessage){
        synchronized (this){
            messages.addLast(activeMessage);
            this.notify();
        }
    }

    public ActiveMessage take(){
        synchronized(this){
            //当MethodMessage队列中没有Message的时候,执行线程进入阻塞
            while(messages.isEmpty()){
                try{
                    this.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

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
                ActiveMessage methodMessage = this.queue.take();
                methodMessage.execute();

            }
        }
    }
}
