package top.youlanqiang.threadlean.book.lesson29.chat;

import top.youlanqiang.threadlean.book.lesson29.async.AsyncChannel;
import top.youlanqiang.threadlean.book.lesson29.synchronize.Event;

//用户聊天的Event,直接在控制台输出即可
public class UserChatEventChannel extends AsyncChannel {

    @Override
    protected void handle(Event message) {
        UserChatEvent event = (UserChatEvent) message;
        System.out.println("The user[" + event.getUser().getName() + "] say: " + event.getMessage());
    }
}
