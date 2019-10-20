package top.youlanqiang.threadlean.book.lesson27;

/**
 * 这个Factory工具类，主要是为了让Proxy的构造透明化
 */
public final class OrderServiceFactory {

    //将ActiveMessageQueue定义成static的目的是，保持其在整个JVM进程中是唯一的，并且ActiveDaemonThread会在此刻启动
    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();

    //不允许new的方式构建
    private  OrderServiceFactory(){}

    //返回orderServiceProxy
    public static OrderService toActiveObject(OrderService orderService){
           return new OrderServiceProxy(orderService, activeMessageQueue);
    }


}
