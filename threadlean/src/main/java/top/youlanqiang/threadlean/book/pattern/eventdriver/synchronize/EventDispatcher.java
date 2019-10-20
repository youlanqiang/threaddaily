package top.youlanqiang.threadlean.book.pattern.eventdriver.synchronize;

import java.util.HashMap;
import java.util.Map;

/**
 * 是对 DynamicRouter的一个基本实现
 * 不是一个线程安全的类
 */
public class EventDispatcher implements DynamicRouter<Message> {

    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher(){
        //初始化RouterTable 但是使用HashMap来做路由表
        this.routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if(routerTable.containsKey(message.getType())){
            //直接获取对应的Channel处理Message
            routerTable.get(message.getType()).dispatch(message);

        }else{
            throw new MessageMatcherException("Can't match the channel for [" + message.getType() +"] type");
        }
    }
}
