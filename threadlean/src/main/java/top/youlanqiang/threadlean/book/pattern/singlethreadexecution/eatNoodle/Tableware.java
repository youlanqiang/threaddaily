package top.youlanqiang.threadlean.book.singlethreadexecution.eatNoodle;

/**
 * 餐具类
 */
public class Tableware {

    //餐具名称
    private final String toolName;

    public Tableware(String toolName){
        this.toolName = toolName;
    }


    @Override
    public String toString() {
        return "Tool:" + toolName;
    }
}
