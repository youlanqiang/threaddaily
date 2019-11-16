package top.youlanqiang.threadlean.juc.utils.phaser;

import java.util.concurrent.Phaser;

public class PhaserExample5 {

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(3);
        new Thread(phaser::arriveAndAwaitAdvance).start();
    }

}
