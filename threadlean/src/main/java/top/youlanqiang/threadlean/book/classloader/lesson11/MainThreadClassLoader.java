package top.youlanqiang.threadlean.book.lesson11;

public class MainThreadClassLoader {


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
