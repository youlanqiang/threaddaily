package top.youlanqiang.threadlean.book.threadpool.training.impl;

import top.youlanqiang.threadlean.book.threadpool.training.MyThreadPool;

public class MyBasicThreadPool implements MyThreadPool {



    @Override
    public void execute(Runnable runnable) {

    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public int getCoreSize() {
        return 0;
    }

    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public int getActiveSize() {
        return 0;
    }

    @Override
    public int getQueueSize() {
        return 0;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public boolean isShutdown() {
        return false;
    }



}
