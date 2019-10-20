package top.youlanqiang.threadlean.book.pattern.activeobject.common;


import top.youlanqiang.threadlean.book.pattern.future.Future;
import top.youlanqiang.threadlean.book.pattern.activeobject.ActiveFuture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通用的ActiveObjects的核心类，其负责生成Service的代理以及构建ActiveMessage
 */
public class ActiveServiceFactory {

    //定义ActiveMessageQueue 用于存放ActiveMessage
    private final static ActiveMessageQueue queue = new ActiveMessageQueue();


    public static <T> T active(T instance){
        Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader()
        , instance.getClass().getInterfaces(), new ActiveInvocationHandler<>(instance));
        return (T) proxy;
    }

    //用来生成Proxy时需要用到
    private static class ActiveInvocationHandler<T> implements InvocationHandler{
        private final T instance;

        ActiveInvocationHandler(T instance){
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果接口方法被@ActiveMessage标记，则会转换为ActiveMessage
            if(method.isAnnotationPresent(ActiveMethod.class)){
                this.checkMethod(method);
                Builder builder = new Builder();
                builder.useMethod(method).withObjects(args).forService(instance);
                Object result = null;
                if(this.isReturnFutureType(method)){
                    result = new ActiveFuture<>();
                    builder.returnFuture((ActiveFuture) result);
                }
                //将ActiveMessage加入队列中
                queue.offer(builder.build());
                return result;
            }else{
                //普通对象正常执行
                return method.invoke(instance, args);
            }

        }

        //检查有返回值的方法是否为Future. 否则会抛异常
        private void checkMethod(Method method){
            //有返回值
            if(!isReturnVoidType(method) && !isReturnFutureType(method)){
                throw new RuntimeException("the method {" + method.getName() + " return type must be void/Future");
            }
        }

        //判断方法是否为Future返回值
        private boolean isReturnFutureType(Method method){
            return method.getReturnType().isAssignableFrom(Future.class);
        }

        //判断方法是否无返回类型
        private boolean isReturnVoidType(Method method){
            return method.getReturnType().equals(Void.TYPE);
        }

    }
}
