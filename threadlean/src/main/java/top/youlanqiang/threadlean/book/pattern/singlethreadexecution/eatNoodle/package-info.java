package top.youlanqiang.threadlean.book.pattern.singlethreadexecution.eatNoodle;

/**
 * 哲学家吃面问题
 * 是解释操作系统中多个进程竞争资源的经典问题
 * 每个哲学家的左右手都有吃面用的刀叉，但是不足以同时使用
 * 比如A哲学家想要吃面，必须拿起左手边的叉和右手边的刀
 * 但是很有可能叉和刀都被其他哲学家拿走使用
 * 或者是手持刀等待别人放下叉等容易引起死锁的问题
 */