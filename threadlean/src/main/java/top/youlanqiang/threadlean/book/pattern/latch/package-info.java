package top.youlanqiang.threadlean.book.pattern.latch;

/**
 * Latch设计模式
 * 若干线程并发执行某个特定的任务
 * 然后等到所有的子任务都执行结束之后再统一汇总
 * 为了保证运行数据库的数据量在一个恒定的范围之内
 * 这个就是Latch(门阀)设计模式
 */