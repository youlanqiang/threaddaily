package top.youlanqiang.threadlean.book.pattern.eventdriver.chat;

import top.youlanqiang.threadlean.book.pattern.eventdriver.synchronize.Event;

public class UserOnlineEvent extends Event {

    private final User user;

    public UserOnlineEvent(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

}
