package top.youlanqiang.threadlean.book.pattern.eventdriver.synchronize;

public class EventDispatcherExample {


    public static class InputEvent extends Event{

        private final int x;
        private final int y;

        public InputEvent(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }
    }

    /**
     * 用于存放结果的Event
     */
    public static class ResultEvent extends Event{
        private final int result;

        public ResultEvent(int result){
            this.result = result;
        }

        public int getResult(){
            return result;
        }
    }

    /**
     * 处理message的channel
     */
    static class ResultEventHandler implements Channel<ResultEvent>{

        @Override
        public void dispatch(ResultEvent message) {
            System.out.println("The result is: " + message.getResult());
        }
    }

    /**
     * InputEventHandler需要向Router发送Event,因此在构造的时候需要传入Dispatcher
     */
    static class InputEventHandler implements Channel<InputEvent>{

        private final EventDispatcher dispatcher;

        public InputEventHandler(EventDispatcher dispatcher){
            this.dispatcher = dispatcher;
        }

        /**
         * 将结果发送给router
         * @param message
         */
        @Override
        public void dispatch(InputEvent message) {
            System.out.printf("X:%d,Y:%d\n", message.getX(), message.getY());
            int result = message.getX() + message.getY();
            dispatcher.dispatch(new ResultEvent(result));
        }
    }

    public static void main(String[] args) {
        //构造Router
        EventDispatcher dispatcher = new EventDispatcher();

        //将Event和Handler（Channel）的绑定关系注册到Dispatcher
        dispatcher.registerChannel(InputEvent.class, new InputEventHandler(dispatcher));
        dispatcher.registerChannel(ResultEvent.class, new ResultEventHandler());
        dispatcher.dispatch(new InputEvent(1, 2));
        /**
         * X:1,Y:2
         * The result is: 3
         */
    }
}
