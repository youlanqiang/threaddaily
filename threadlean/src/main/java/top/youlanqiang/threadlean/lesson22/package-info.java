package top.youlanqiang.threadlean.lesson22;

/**
 * Balking设计模式
 * A线程监控到共享变量发生变化后即将触发某个操作
 * 但是此时发现有另外一个线程B已经针对该变量的变化开始了行动
 * 因此A便放弃了准备开始的工作，
 * 我们把这样的线程间交互称为Balking(犹豫)设计模式
 */