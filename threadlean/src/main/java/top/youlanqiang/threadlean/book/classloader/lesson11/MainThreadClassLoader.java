package top.youlanqiang.threadlean.book.classloader.lesson11;

public class MainThreadClassLoader {


    public static void main(String[] args) {
        //ApplicationClassLoader负责加载classpath下的class,第三方jar,自定义类
        //AppClassLoader的父类加载器是ExtClassLoader
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}
