package top.youlanqiang.threadlean.lesson28.test;

import top.youlanqiang.threadlean.lesson28.AsyncEventBus;
import top.youlanqiang.threadlean.lesson28.Bus;
import top.youlanqiang.threadlean.lesson28.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {

    public static void main(String[] args) {
        Bus bus = new EventBus("TestBus");

        //创建一个异步的Bus
        //Bus asynBus = new AsyncEventBus("TestBus", (ThreadPoolExecutor) Executors.newFixedThreadPool(10));
        bus.register(new SimpleSubscriber1());
        bus.register(new SimpleSubscriber2());
        bus.post("hello");
        System.out.println("---------------");
        bus.post("hello", "test");

//        ==SimpleSubscriber1==method1==hello
//        ==SimpleSubscriber2==method1==hello
//        ---------------
//        ==SimpleSubscriber1==method2==hello
//        ==SimpleSubscriber2==method2==hello

    }
}
