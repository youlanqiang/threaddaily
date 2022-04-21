package top.youlanqiang.threadlean.book.threadgroup;

import java.util.Arrays;

//一些常用的ThreadGroup API
public class ThreadGroupApi {

    public static void main(String[] args) {
        //activeCount()
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        System.out.println(group.getName()); //group的名称
        System.out.println(group.activeCount()); //group中活跃线程数
        System.out.println(group.activeGroupCount());//group中的子group

        Thread[] threads = new Thread[group.activeCount()];
        group.enumerate(threads); //获取group中所有的线程
        Arrays.asList(threads).forEach(System.out::println);
    }
}
