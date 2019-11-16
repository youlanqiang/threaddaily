package top.youlanqiang.threadlean.juc.utils.phaser;

import java.util.concurrent.Phaser;

public class PhaserExample4 {

    public static void main(String[] args) {
//        final Phaser phaser = new Phaser(1);

//        System.out.println(phaser.getRegisteredParties()); 1
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties()); 2
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties()); 3

//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());

        //批量注册register
//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());
//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());
//        new Thread(phaser::arriveAndAwaitAdvance).start();


        final Phaser phaser = new Phaser(2){

            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return true;
            }
        };
    }

    static class OnAdvanceTask extends Thread{

        private final Phaser phaser;

        OnAdvanceTask(String name, Phaser phaser){
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName()+" I am start and the phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " I am end!");
        }
    }
}
