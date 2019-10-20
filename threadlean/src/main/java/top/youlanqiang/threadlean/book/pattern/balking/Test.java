package top.youlanqiang.threadlean.book.pattern.balking;

public class Test {

    public static void main(String[] args) {
        new DocumentEditThread("/Users/youlanqiang/IdeaProjects/threaddaily", "balking.txt").start();
    }
}
