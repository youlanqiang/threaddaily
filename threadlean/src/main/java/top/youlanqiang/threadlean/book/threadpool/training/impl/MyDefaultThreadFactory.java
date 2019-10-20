package top.youlanqiang.threadlean.book.lesson8.training.impl;

import top.youlanqiang.threadlean.book.lesson8.training.MyThreadFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class MyDefaultThreadFactory implements MyThreadFactory {

    private static final AtomicInteger  atomicInteger = new AtomicInteger(0);

    private static final ThreadGroup threadGroup = new ThreadGroup("ThreadGroup-1");

    @Override
    public Thread create(Runnable runnable) {

        return new Thread(threadGroup, runnable, "My Thread--" + atomicInteger.getAndIncrement());
    }
}
