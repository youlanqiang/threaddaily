package top.youlanqiang.threadlean.book.lesson22;

public class Test {

    public static void main(String[] args) {
        new DocumentEditThread("/Users/youlanqiang/IdeaProjects/threaddaily", "balking.txt").start();
    }
}
