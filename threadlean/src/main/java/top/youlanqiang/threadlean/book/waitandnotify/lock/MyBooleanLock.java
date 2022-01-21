package top.youlanqiang.threadlean.book.waitandnotify.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MyBooleanLock implements Lock {


    @Override
    public void lock() throws InterruptedException {

    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {

    }

    @Override
    public void unlock() {

    }

    @Override
    public List<Thread> getBlockedThreads() {
        return null;
    }
}
