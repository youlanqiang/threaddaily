package top.youlanqiang.threadlean;

import java.util.concurrent.TimeUnit;

public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread(
                () -> {
                    Thread runner = new Thread(task);
                    /**
                     * 设置 runner为当前 executeThread的守护线程
                     * 当executeThread线程退出时,runner才会退出.
                     */
                    runner.setDaemon(true);

                    runner.start();

                    try {
                        /**
                         * 使用 runner.join
                         * runner是executeThread的守护线程
                         * executeThread会一直等待runner执行结束
                         */
                        runner.join();
                        finished = true;
                        System.out.println("任务正常执行完毕。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );

        executeThread.start();
    }

    public void shutdown(long seconds) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime)/1000 >= seconds) {
                System.out.println("任务超时! 需要结束。");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("线程被打断。");
                break;
            }
        }
        finished = false;
    }

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        service.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10);
    }
}
