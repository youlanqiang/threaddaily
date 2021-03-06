package top.youlanqiang.threadlean.book.pattern.readwritelock;

/**
 * 读写锁
 * 允许多个线程在同一时间对共享资源进行读取操作，
 * 在读取明显多于写的场合下，其对性能的提升是非常明显的，
 * 但是如果使用不当性能反倒会比较差,比如在写线程的数量和读线程的数量接近甚至多于读线程情况下
 * 因此在jdk1.8中又增加了，StampedLock的解决方案
 */