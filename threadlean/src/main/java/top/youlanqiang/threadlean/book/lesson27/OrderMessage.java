package top.youlanqiang.threadlean.book.lesson27;

import java.util.Map;

/**
 * 主要处理order方法，从param中获取参数接口
 * 执行orderService的order方法
 */
public class OrderMessage extends MethodMessage{

    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        //获取参数
        String account = (String) params.get("account");
        long orderId = (long) params.get("orderId");

        //执行真正的order方法
        orderService.order(account, orderId);

    }
}
