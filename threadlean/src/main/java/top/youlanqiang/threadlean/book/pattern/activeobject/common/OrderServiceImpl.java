package top.youlanqiang.threadlean.book.pattern.activeobject.common;

import top.youlanqiang.threadlean.book.pattern.future.Future;
import top.youlanqiang.threadlean.book.pattern.future.FutureService;
import top.youlanqiang.threadlean.book.pattern.activeobject.OrderService;

import java.util.concurrent.TimeUnit;

/**
 * 该类是在执行线程中将要被使用的类
 */
public class OrderServiceImpl implements OrderService {


    @ActiveMethod
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(
                input -> {
                    try{
                        //通过休眠来模拟该方法的执行比较耗时
                        TimeUnit.SECONDS.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    return "The order Details Information";
                }, orderId, null
        );
    }

    @ActiveMethod
    @Override
    public void order(String account, long orderId) {
        try{
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account " + account + ", orderId " + orderId);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
