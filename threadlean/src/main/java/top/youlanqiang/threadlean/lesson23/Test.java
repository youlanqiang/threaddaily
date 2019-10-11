package top.youlanqiang.threadlean.lesson23;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Latch latch = new CountDownLatch(4);

        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Gavin", "Walking").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "Dillon", "Bicycle").start();

        latch.await();
        System.out.println("== all of programmer arrived == ");
    }
}
