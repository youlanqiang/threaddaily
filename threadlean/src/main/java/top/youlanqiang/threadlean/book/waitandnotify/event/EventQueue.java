package top.youlanqiang.threadlean.book.waitandnotify.event;

import java.util.LinkedList;

/**
 * EventQueue
 * 如果队列中有Event则通知线程开始工作
 */

public class EventQueue {

    private final int max; //最大Event

    static class Event{

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue(){
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max){
        this.max = max;
    }

    /**
     * offer方法会提交event到队尾
     * 如果此时队列满了，那么提交的队列就会被阻塞（调用wait方法）
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue){
            while(eventQueue.size() >= max){
                try {
                    console(" the queue is full.");
                    eventQueue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    /**
     * 从队头获取数据，如果队列中没有可用数据，那么工作线程会被阻塞。
     * @return
     */
    public Event take(){
        synchronized (eventQueue){
            while(eventQueue.isEmpty()){
                try {
                    console("the queue is empty");
                    eventQueue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console("the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String message){
        System.out.printf("%s:%s\n", Thread.currentThread().getName(), message);
    }


}
