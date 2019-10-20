package top.youlanqiang.threadlean.book.lesson28;

import java.lang.reflect.Method;

/**
 * 主要用于消息推送出错时被回调接口EventExceptionHandler调用
 */
public interface EventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscribe();

    Object getEvent();

}
