package top.youlanqiang.threadlean.book.threadcontext;

/**
 * 程序上下文，整个程序应该只能出现一个
 */
public class ApplicationContext {

    private ApplicationContext(){ }

    /**
     * 使用Holder的方式实现单例
     */
    private static class Holder{
        private static ApplicationContext instance = new ApplicationContext();
    }


    public static ApplicationContext getContext(){
        return Holder.instance;
    }

}
