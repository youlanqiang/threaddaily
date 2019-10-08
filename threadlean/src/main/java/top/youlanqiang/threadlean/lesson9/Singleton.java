package top.youlanqiang.threadlean.lesson9;

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
