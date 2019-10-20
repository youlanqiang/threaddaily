package top.youlanqiang.threadlean.book.pattern.eventbus;

import java.util.concurrent.Executor;

/**
 * 同步EventBus是最核心的一个类，它实现了Bus的所有的功能,
 * 但是该类对Event的广播推送采用的是同步的方式
 * 如果想用异步方式进行推送，就使用EventBus的子类
 */
public class EventBus implements Bus {

    //用于维护subscriber的注册表
    private final Registry registry = new Registry();

    //EventBus的名字
    private String busName;

    //默认的EventBus的名字
    private final static String DEFAULT_BUS_NAME = "default";

    //默认的topic的名字
    private final static String  DEFAULT_TOPIC = "default-topic";

    //用于分发广播消息到各个subscribe的表
    private final Dispatcher dispatcher;

    public EventBus(){
        this(DEFAULT_BUS_NAME);
    }

    public EventBus(String busName){
        this(busName, null, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }


    public EventBus(EventExceptionHandler exceptionHandler){
        this(DEFAULT_BUS_NAME, exceptionHandler, Dispatcher.SEQ_EXECUTOR_SERVICE);
    }

    EventBus(String busName, EventExceptionHandler exceptionHandler, Executor executor){
        this.busName = busName;
        this.dispatcher = Dispatcher.newDispatcher(exceptionHandler, executor);
    }

    //将注册Subscriber的动作直接委托给Registry
    @Override
    public void register(Object subscriber) {
        this.registry.bind(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        this.registry.unbind(subscriber);
    }

    //将Event到默认的topic
    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }

    //提交Event到指定的topic,具体的动作是由Dispatcher来完成的
    @Override
    public void post(Object event, String topic) {
        this.dispatcher.dispatcher(this, registry, event, topic);
    }

    //关闭销毁Bus
    @Override
    public void close() {
        this.dispatcher.close();
    }

    @Override
    public String getBusName() {
        return this.busName;
    }
}
