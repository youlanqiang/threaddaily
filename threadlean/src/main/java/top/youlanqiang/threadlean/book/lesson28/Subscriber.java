package top.youlanqiang.threadlean.book.lesson28;


import java.lang.reflect.Method;

/**
 * subscriber类封装了对象实例和被@Subscribe标记的方法,
 * 也就是说一个对象实例会被封装成若干个Subscriber
 */
public class Subscriber {


    private final Object subscribeObject;

    private final Method subscribeMethod;

    private boolean disable = false;

    public Subscriber(Object subscribeObject, Method subscribeMethod){
        this.subscribeObject = subscribeObject;
        this.subscribeMethod = subscribeMethod;
    }

    public Object getSubscribeObject() {
        return subscribeObject;
    }

    public Method getSubscribeMethod() {
        return subscribeMethod;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
