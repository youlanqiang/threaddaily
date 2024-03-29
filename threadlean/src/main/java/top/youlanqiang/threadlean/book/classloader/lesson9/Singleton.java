package top.youlanqiang.threadlean.book.classloader.lesson9;

/**
 * 将注释2的代码移动到注释1的位置，输出结果会发生变化，这是为什么？
 */
public class Singleton {

    //1
    private static Singleton instance = new Singleton();
    //private static int x = 0;

    private static int y;

    //2
    private static int x = 0;
    //private static Singleton instance = new Singleton();

    private Singleton(){
        x++;
        y++;
    }

    public static Singleton getInstance(){
        return instance;
    }


    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);// 1 0
        System.out.println(singleton.y);// 1 1
    }
}
