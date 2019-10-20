package top.youlanqiang.threadlean.book.pattern.activeobject.common;

import top.youlanqiang.threadlean.book.pattern.activeobject.ActiveFuture;

import java.lang.reflect.Method;

public class Builder {

    Object[] objects;

    Method method;

    ActiveFuture<Object> future;

    Object service;

    public Builder useMethod(Method method){
        this.method = method;
        return this;
    }

    public Builder returnFuture(ActiveFuture<Object> future){
        this.future = future;
        return this;
    }

    public Builder withObjects(Object[] objects){
        this.objects = objects;
        return this;
    }

    public Builder forService(Object service){
        this.service = service;
        return this;
    }

    public ActiveMessage build(){
        return new ActiveMessage(this);
    }

}
