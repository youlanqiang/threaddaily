package top.youlanqiang.threadlean.book.threadpool.train;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author youlanqiang
 * created in 2022/1/25 10:30 上午
 */
public class RunnableQueue {

    private final Deque<Runnable>  list;

    private final DenyPolicy denyPolicy;

    private final int limit;

    public RunnableQueue(int limit, DenyPolicy denyPolicy){
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.list = new LinkedList<>();
    }

    public Runnable take() throws InterruptedException {
        synchronized(list){
            while(list.isEmpty()){
                list.wait();
            }
            return list.removeLast();
        }
    }

    public void offer(Runnable runnable){
        synchronized(list){
            if(list.size() >= limit){
              denyPolicy.reject(runnable);
            }else{
                list.addFirst(runnable);
                list.notifyAll();
            }
        }
    }

    public int size(){
        synchronized(list){
            return list.size();
        }
    }

}
