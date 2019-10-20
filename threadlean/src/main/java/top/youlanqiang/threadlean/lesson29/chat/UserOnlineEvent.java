package top.youlanqiang.threadlean.lesson29.chat;

import top.youlanqiang.threadlean.lesson29.synchronize.Event;

public class UserOnlineEvent extends Event {

    private final User user;

    public UserOnlineEvent(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

}