package top.youlanqiang.threadlean.book.pattern.activeobject.common;

import top.youlanqiang.threadlean.book.pattern.future.Future;
import top.youlanqiang.threadlean.book.pattern.activeobject.OrderService;

public class Test {

    public static void main(String[] args) throws InterruptedException{
        OrderService orderService = ActiveServiceFactory.active(new OrderServiceImpl());
        Future<String> future = orderService.findOrderDetails(23423);
        System.out.println("I will be returned immediately");
        System.out.println(future.get());
    }
}
