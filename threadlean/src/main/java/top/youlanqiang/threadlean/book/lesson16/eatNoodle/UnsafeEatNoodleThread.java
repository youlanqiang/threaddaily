package top.youlanqiang.threadlean.book.lesson16.eatNoodle;

public class UnsafeEatNoodleThread extends Thread {

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new UnsafeEatNoodleThread("A", fork, knife).start();
        new UnsafeEatNoodleThread("B", fork, knife).start();
    }



    private final String name;

    private final Tableware leftTool;

    private final Tableware rightTool;


    public UnsafeEatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }


    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + " take up " + leftTool + "(left)");
            synchronized (rightTool) {
                System.out.println(name + " take up " + rightTool + "(right)");
                System.out.println(name + " is eating now.");
                System.out.println(name + " put down " + rightTool + "(right)");
            }
            System.out.println(name + " put down " + leftTool + "(left)");
        }
    }
}
