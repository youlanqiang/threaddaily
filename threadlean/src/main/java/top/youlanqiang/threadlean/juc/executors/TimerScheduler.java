package top.youlanqiang.threadlean.juc.executors;

import java.util.Timer;
import java.util.TimerTask;

public class TimerScheduler {

    /**
     * Java定时任务框架
     * scheduler solution
     * Timer/TimerTask
     * SchedulerExecutorService
     * crontab
     * cron4j
     * quartz
     * @param args
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("======" + System.currentTimeMillis());
            }
        };
        timer.schedule(task, 1000, 1000);
    }


}
