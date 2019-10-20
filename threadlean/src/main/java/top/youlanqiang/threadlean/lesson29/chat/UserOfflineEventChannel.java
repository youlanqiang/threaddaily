package top.youlanqiang.threadlean.lesson29.chat;

import top.youlanqiang.threadlean.lesson29.async.AsyncChannel;
import top.youlanqiang.threadlean.lesson29.synchronize.Event;

//用户下线的Event 简单输出用户下线即可
public class UserOfflineEventChannel extends AsyncChannel {


    @Override
    protected void handle(Event message) {
        UserOfflineEvent event = (UserOfflineEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] is offline.");
    }

}
