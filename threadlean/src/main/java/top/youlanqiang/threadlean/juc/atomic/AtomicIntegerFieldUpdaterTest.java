package top.youlanqiang.threadlean.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/** 基于反射的实用工具，可以对指定类的指定 volatile 字段进行原子更新。
 * 该类用于原子数据结构，该结构中同一节点的几个引用字段都独立受原子更新控制。例如，树节点可能声明为
 */
 public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        // 类和字段名
        AtomicIntegerFieldUpdater<TestMe> updater =
                AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();


        for(int i = 0; i < 2; i++){
            new Thread(){
                @Override
                public void run() {
                    final int MAX = 20;
                    for(int i = 0; i < MAX; i++){
                        int v = updater.getAndIncrement(me);
                        System.out.println(Thread.currentThread().getName() + "=>" + v);
                    }
                }
            }.start();
        }

        //AtomicIntegerFieldUpdater 不能访问非本类的私有field对象

    }

    static class TestMe{
        volatile int i;
    }
}
