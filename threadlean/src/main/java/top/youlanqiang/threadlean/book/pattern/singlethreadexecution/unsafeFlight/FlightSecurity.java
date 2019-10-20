package top.youlanqiang.threadlean.book.singlethreadexecution.unsafeFlight;

/**
 * 模拟一个非线程安全的安检口类，
 * 旅客分别手持登机牌和身份证接受工作人员的检查
 */
public class FlightSecurity {

    private int count = 0;

    //登机牌
    private String boardingPass = "null";

    //身份证
    private String idCard = "null";

    /**
     * 将程序改为线程安全的类也很简单，只需要给pass加上synchronized关键字
     */
    public void pass(String boardingPass, String idCard){
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check(){
        //简单的模拟测试，当登机牌和身份证首字母不相同时则表示检查不通过
        if(boardingPass.charAt(0) != idCard.charAt(0)){
            throw new RuntimeException("====Exception====" + toString());
        }
    }

    @Override
    public String toString() {
        return "FlightSecurity{" +
                "count=" + count +
                ", boardingPass='" + boardingPass + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
