package top.youlanqiang.threadlean.book.lesson28.monitor;

import top.youlanqiang.threadlean.book.lesson28.AsyncEventBus;
import top.youlanqiang.threadlean.book.lesson28.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * 2
        );
        final EventBus eventBus = new AsyncEventBus(executor);

        //注册
        eventBus.register(new FileChangeListener());
        DirectoryTargetMonitor monitor = new DirectoryTargetMonitor(eventBus, "path");
        monitor.startMonitor();

    }
}
