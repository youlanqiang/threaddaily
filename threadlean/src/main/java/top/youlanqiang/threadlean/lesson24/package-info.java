package top.youlanqiang.threadlean.lesson24;


/**
 * Thread-Per-Message模式
 * 为每个消息的处理开辟一个线程使得消息能够以并发方式进行处理
 * 需要使用线程池技术，不要开辟过多的线程
 * 常用于多用户的网络聊天。
 */