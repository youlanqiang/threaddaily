package top.youlanqiang.threadlean.book.waitandnotify.event;


import java.util.LinkedList;

/**
 * @author youlanqiang
 * created in 2022/1/21 4:05 下午
 * 用于练习 object中的notify和wait方法的使用
 */
public class TestMyEventQueue {

    public static void main(String[] args) {
        TestMyEventQueue queue = new TestMyEventQueue();
        Thread producer = new Thread(()->{
            for (;;) {
                queue.offer(1);
            }
        });
        producer.start();

        Thread consumer = new Thread(()->{
           for(;;){
               int result =  queue.take();
               System.out.println("已消费:"+result);
           }
        });
        consumer.start();
    }

    private int max = 10;

    private final LinkedList<Integer> list = new LinkedList<>();

    public void offer(int event){
        synchronized(list){
            while(list.size() >= max){
                try {
                    list.wait();
                    System.out.println("队列已满");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(event);
            System.out.println("已提交event:"+event);
            list.notifyAll();
        }
    }

    public Integer take(){
        synchronized(list){
            while(list.isEmpty()){
                try {
                    list.wait();
                    System.out.println("队列为空");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int result =  list.removeFirst();
            list.notifyAll();
            return result;
        }
    }

}
