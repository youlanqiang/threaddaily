package top.youlanqiang.threadlean.lesson8;

/**
 * 线程池原理以及自定义线程池
 *
 * 一个完整的线程池应该具备如下要素:
 * 任务队列:用于缓存提交的任务
 *
 * 线程数量管理功能: 一个线程池必须能够很好的管理和控制线程数量。
 * 可以通过三个参数来实现，
 * 1. 比如创建线程池时初始化的线程数量init;
 * 2. 线程池自动扩充时最大的线程数量max；
 * 3. 在线程池空闲时需要释放线程但是也要维护一定数量的活跃数量或者核心数量core;
 * 三者的关系是 init<=core<=max
 *
 * 任务拒绝策略: 如果线程数量已达到上限且任务队列已满，则需要有相应的拒绝策略来通知任务提交者
 *
 * 线程工厂: 主要用于个性化定制线程，比如将线程设置为守护线程以及设置线程名称
 *
 * QueueSize: 任务队列主要存放提交的Runnable,但是为了防止内存满，需要有limit数量对其进行控制
 *
 * Keepedalive时间: 该时间主要决定线程各个重要参数自动维护的时间间隔
 *
 */