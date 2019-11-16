package top.youlanqiang.threadlean.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PhaserExample1 {

    private final static Random random = new Random(System.currentTimeMillis());


    public static void main(String[] args) {

        final Phaser phaser = new Phaser();

        IntStream.rangeClosed(1, 5).boxed().map(x->phaser)
                .forEach(Task::new);

        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("all of down.");
    }

    static class Task extends Thread{
        private final Phaser phaser;

        Task(Phaser phaser){
            this.phaser = phaser;
            this.phaser.register();
            start();
        }

        public void run(){
            System.out.println("The Worker [" + getName() + "] is working...");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
        }
    }
}
