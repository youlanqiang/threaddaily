package top.youlanqiang.threadlean;

import java.util.concurrent.TimeUnit;

public class MyThreadService {

    private Thread executeThread;

    private boolean finished;



    public void execute(Runnable runnable){
        finished = false;
        executeThread = new Thread(()->{
            Thread runner = new Thread(runnable);
            runner.setDaemon(true);
            runner.start();

            try {
                runner.join();
                finished = true;
                System.out.println("程序正常执行完毕.");
            } catch (InterruptedException e) {
                System.out.println("程序被打断.");
            }
        });

        executeThread.start();
    }

    public void shutdown(int seconds){
        long currentTime = System.currentTimeMillis();
        while(!finished){
            if((System.currentTimeMillis() - currentTime)/1000 >= seconds){
                //打断 executeThread;
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            }catch(InterruptedException e){
                System.out.println("程序被打断.");
                break;
        }
        }

    }

    public static void main(String[] args) {
        MyThreadService service = new MyThreadService();
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
