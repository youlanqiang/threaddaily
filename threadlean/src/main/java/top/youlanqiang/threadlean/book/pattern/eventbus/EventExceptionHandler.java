package top.youlanqiang.threadlean.book.pattern.eventbus;



public interface EventExceptionHandler {

    void handle(Throwable cause, EventContext context);

}
