package top.youlanqiang.threadlean.book.lesson9;

/**
 * JVM虚拟机规范规定了，
 * 每个类或者接口被Java程序首次主动使用时才会对其进行初始化
 * 随着JIT技术越来越成熟，JVM运行期间的编译也越来越智能
 * 不排除JVM在运行期间提前预判并且初始化某个类
 */
public class UsedTest {

    /**
     * JVM规范的6种主动使用类的场景
     *
     * 1。通过new关键字会导致类的初始化
     * 2。访问类的静态变量
     */

    public static int x = 10;

    /**
     * 3。访问类的静态方法
     */

    public static void test(){
        System.out.println("say something.");
    }

    /**
     * 4。对某个类进行反射操作
     */
    public static void main(String[] args) throws Exception{
        Class.forName("top.youlanqiang.threadlean.book.lesson9.UsedTest");
    }

    /**
     * 5。初始化子类会导致父类的初始化
     * 使用子类直接访问父类的静态变量，并不会导致子类初始化
     */

    /**
     * 6。启动类，main函数所在的类会导致该类的初始化
     */


    /**
     * 除了以上6种情况，其余的都被称为被动使用，不会导致类的加载和初始化
     */

    /**
     * 构造某个类的数组时并不会导致该类的初始化
     */
    public void test2(){
        UsedTest[] usedTests = new UsedTest[]{};
    }

    /**
     * 引用类的静态常量不会导致类的初始化
     */
    public final static int MAX = 100;
}
