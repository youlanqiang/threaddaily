package top.youlanqiang.threadlean.book.pattern.eventdriver.chat;

import top.youlanqiang.threadlean.book.pattern.eventdriver.async.AsyncChannel;
import top.youlanqiang.threadlean.book.pattern.eventdriver.synchronize.Event;

//用户上线的Event，简单输出用户上线即可
public class UserOnlineEventChannel extends AsyncChannel {


    @Override
    protected void handle(Event message) {
        UserOnlineEvent event = (UserOnlineEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] is online.");

    }
}
