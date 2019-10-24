package top.youlanqiang.threadlean.juc.atomic;

/**
 * 原子类型
 * AtomicInteger
 * AtomicBoolean
 * AtomicLong
 * AtomicReference
 * AtomicStampedReference
 * AtomicIntegerArray
 * AtomicLongArray
 * AtomicReferenceArray
 * AtomicIntegerFieldUpdater
 * AtomicLongFieldUpdater
 * AtomicReferenceFieldUpdater
 * --个基于反射的工具类，它能对指定类的指定的volatile引用字段进行原子更新。(注意这个字段不能是private的)
 * Unsafe
 *
 * 1.想让类的属性操作具有原子性
 * 2.不想使用锁 Lock和Synchronized
 * 3.大量需要原子类型修饰的对象，比较消耗内存
 *
 * CAS大致实现
 * public boolean compareAndSwap(int v,int a,int b) {
 * 		if (v == a) {
 * 			v = b;
 * 			return true;
 *       }else {
 * 			return false;
 *       }
 *  }
 */