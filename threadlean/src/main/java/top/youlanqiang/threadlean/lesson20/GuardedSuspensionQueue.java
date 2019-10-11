package top.youlanqiang.threadlean.lesson20;

import java.util.LinkedList;

public class GuardedSuspensionQueue {

    private final LinkedList<Integer> queue = new LinkedList<>();

    private final int LIMIT = 100;

    public void offer(Integer data) throws InterruptedException{
        synchronized (this){
            //判断queue的当前元素是否超过了最大容量，则会陷入阻塞
            while(queue.size() >= LIMIT){
                this.wait();
            }
            //插入元素并且唤醒take线程
            queue.addLast(data);
        }
    }

    public Integer take() throws InterruptedException{
        synchronized (this){
            //判断如果队列为空
            while(queue.isEmpty()){
                //则挂起当前线程
                this.wait();
            }
            //通知offer线程可以继续插入数据了
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
