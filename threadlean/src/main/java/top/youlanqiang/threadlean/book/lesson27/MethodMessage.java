package top.youlanqiang.threadlean.book.lesson27;

import java.util.Map;

/**
 * 主要作用是收集每一个接口的方法参数
 * 并且提供execute方法供ActiveDaemonThread直接调用
 * 该对象就是典型的Worker Thread 模型中的Product
 */
public abstract class MethodMessage {

    //用于收集方法参数,如果又返回Future类型则一并收集
    protected final Map<String, Object> params;

    protected final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService){
        this.params = params;
        this.orderService = orderService;
    }

    //抽象方法，扮演work thread的说明书
    public abstract void execute();


}
