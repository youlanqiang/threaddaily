package top.youlanqiang.threadlean.book.lesson27;

public class Test {

    public static void main(String[] args) throws Exception{
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());
        orderService.order("hello", 453453);
        //立即返回
        System.out.println("Return immediately");

        Thread.currentThread().join();
    }
}
