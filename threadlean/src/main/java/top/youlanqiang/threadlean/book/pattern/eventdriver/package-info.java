package top.youlanqiang.threadlean.book.pattern.eventdriver;

/**
 * EventDriver 模式
 * 是一种实现组件之间松耦合,易扩展的架构方式
 *  Events：需要被处理的数据
 *  Event Handlers: 处理Events的方式方法
 *  Event Loop: 维护Events 和Event Handlers之间的交互流程
 *  EventA 会被 HandlerA处理 EventB 会被 HandlerB处理,Event的分配都是被Loop控制的
 */