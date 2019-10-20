package top.youlanqiang.threadlean.book.pattern.eventdriver.chat;

public class UserChatEvent extends UserOnlineEvent {

    //chat event 需要有聊天的信息
    private final String message;

    public UserChatEvent(User user, String message) {
        super(user);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
