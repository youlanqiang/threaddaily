package top.youlanqiang.threadlean.book.pattern.eventdriver.chat;

import top.youlanqiang.threadlean.book.pattern.eventdriver.async.AsyncChannel;
import top.youlanqiang.threadlean.book.pattern.eventdriver.synchronize.Event;

//用户聊天的Event,直接在控制台输出即可
public class UserChatEventChannel extends AsyncChannel {

    @Override
    protected void handle(Event message) {
        UserChatEvent event = (UserChatEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] say: " + event.getMessage());
    }
}
