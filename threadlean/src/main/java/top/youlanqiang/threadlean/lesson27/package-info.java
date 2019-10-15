package top.youlanqiang.threadlean.lesson27;

/**
 * Active Object设计模式
 * 接受异步消息的主动对象
 * 所谓主动对象就是指其拥有自己的独立线程
 * 我们使用的System.gc就是一个接受异步消息的主动对象，
 * 调用gc方法的线程和gc自身的执行线程并不是同一个线程
 *
 */