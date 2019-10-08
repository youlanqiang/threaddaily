package top.youlanqiang.threadlean.lesson8.training;

/**
 * 当任务队列满了之后执行的拒绝策略
 */
public interface MyDenyPolicy {

    /**
     * 执行拒绝策略的方法
     * @param runnable
     * @param threadPool
     */
    void reject(Runnable runnable, MyThreadPool threadPool);


    class DropDenyPolicy implements  MyDenyPolicy{

        @Override
        public void reject(Runnable runnable, MyThreadPool threadPool) {
            //什么都不执行，直接丢弃这个任务
        }
    }

    class ThrowDenyPolicy implements  MyDenyPolicy{

        @Override
        public void reject(Runnable runnable, MyThreadPool threadPool) {
            //抛出异常
            throw new RuntimeException("runnable queue is full !");
        }
    }

    class RunDenyPolicy implements  MyDenyPolicy{

        @Override
        public void reject(Runnable runnable, MyThreadPool threadPool) {
            //直接在该线程运行
            runnable.run();
        }
    }

}
