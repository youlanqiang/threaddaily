package top.youlanqiang.threadlean.book.other;

import java.util.stream.IntStream;

public class TicketWindowRunnable implements Runnable {

    private int index = 1;

    private final static int MAX = 500;

    private final Object MONITOR = new Object();

    @Override
    public void run() {
        while(true){
            synchronized (MONITOR){
                if(index > MAX){
                    break;
                }

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "的号码是:" + (index ++));
            }
        }

    }

    public static void main(String[] args) {
        TicketWindowRunnable runnable = new TicketWindowRunnable();
        IntStream.range(0, 5).forEach((x) ->{
            Thread thread = new Thread(runnable, "线程" + x);
            thread.start();
        });
    }
}
