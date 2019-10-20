package top.youlanqiang.threadlean.book.pattern.eventdriver.chat;

import top.youlanqiang.threadlean.book.pattern.eventdriver.async.AsyncChannel;
import top.youlanqiang.threadlean.book.pattern.eventdriver.synchronize.Event;

//用户下线的Event 简单输出用户下线即可
public class UserOfflineEventChannel extends AsyncChannel {


    @Override
    protected void handle(Event message) {
        UserOfflineEvent event = (UserOfflineEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] is offline.");
    }

}
