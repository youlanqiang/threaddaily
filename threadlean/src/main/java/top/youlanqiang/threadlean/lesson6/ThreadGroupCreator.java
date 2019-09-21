package top.youlanqiang.threadlean.lesson6;

public class ThreadGroupCreator {

    public static void main(String[] args) {
        //获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

        //定义一个新的group
        ThreadGroup group1 = new ThreadGroup("group1");

        //程序输出true
        System.out.println(group1.getParent() == currentGroup);

        //定义group2 指定group1为其父group
        ThreadGroup group2 = new ThreadGroup(group1, "Group2");

        //程序输出true
        System.out.println(group2.getParent() == group1);
    }
}
