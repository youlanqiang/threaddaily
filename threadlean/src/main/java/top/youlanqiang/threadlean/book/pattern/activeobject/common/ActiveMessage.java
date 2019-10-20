package top.youlanqiang.threadlean.book.pattern.activeobject.common;


import top.youlanqiang.threadlean.book.pattern.future.Future;
import top.youlanqiang.threadlean.book.pattern.activeobject.ActiveFuture;

import java.lang.reflect.Method;

/**
 * 相比较于MethodMessage，ActiveMessage更加通用
 */
public class ActiveMessage {

    //接口方法的参数
    private final Object[] objects;

    //接口方法
    private final Method method;

    //有返回值的方法，会返回ActiveFuture<?>类型
    private final ActiveFuture<Object> future;

    //具体的Service接口
    private final Object service;

    ActiveMessage(Builder builder){
        this.objects = builder.objects;
        this.method = builder.method;
        this.future = builder.future;
        this.service = builder.service;
    }

    //ActiveMessage的方法通过反射的形式调用具体的实现
    public void execute(){
        try{
            //执行接口的方法
            Object result = method.invoke(service, objects);
            if(future != null){
                //如果是有返回值的接口方法，则需要通过get方法获取最终结果
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                //将结果交给ActiveFuture,接口方法的线程会得到返回
                future.finish(realResult);
            }
        }catch(Exception e){
            if(future != null){
                future.finish(null);
            }
        }
    }

}
