package top.youlanqiang.threadlean.book.lesson29.chat;

import top.youlanqiang.threadlean.book.lesson29.async.AsyncEventDispatcher;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class UserChatThread extends Thread {

    private final User user;

    private final AsyncEventDispatcher dispatcher;

    public UserChatThread(User user, AsyncEventDispatcher dispatcher) {
        super(user.getName());
        this.user = user;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try{
            //User上线, 发送Online Event
            dispatcher.dispatch(new UserOnlineEvent(user));
            for(int i = 0; i < 5; i++){
                //发送User的聊天信息
                dispatcher.dispatch(new UserChatEvent(user, getName() + "-Hello-" + i));
                //短暂休眠1~10秒
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            //user下线,发送Offline Event
            dispatcher.dispatch(new UserOfflineEvent(user));
        }
    }
}
