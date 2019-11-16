package top.youlanqiang.threadlean.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PhaserExample2 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);

        for (int i = 0; i < 6; i++) {
            new Athletes(i, phaser).start();
        }

    }

    static class Athletes extends Thread{

        private final int no;

        private final Phaser phaser;

        Athletes(int no, Phaser phaser){
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(no + ": start running");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(no + ": end running");

            phaser.arriveAndAwaitAdvance();

            System.out.println(no + ": start bicycle");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(no + ": end bicycle");

            phaser.arriveAndAwaitAdvance();

            System.out.println(no + ": start long jump");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(no + ": end long jump");

            phaser.arriveAndAwaitAdvance();

        }
    }

}
