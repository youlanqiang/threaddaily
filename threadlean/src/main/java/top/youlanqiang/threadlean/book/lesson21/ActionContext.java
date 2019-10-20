package top.youlanqiang.threadlean.book.lesson21;

public class ActionContext {

    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);

    public static Context get(){
        return context.get();
    }


    //每一个线程都有一个独立的Context实例
    static class Context{

        //在Context中的其它成功
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
