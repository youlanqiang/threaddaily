package top.youlanqiang.threadlean.book.pattern.eventdriver.chat;

public class UserOfflineEvent extends UserOnlineEvent {

    public UserOfflineEvent(User user) {
        super(user);
    }
}
