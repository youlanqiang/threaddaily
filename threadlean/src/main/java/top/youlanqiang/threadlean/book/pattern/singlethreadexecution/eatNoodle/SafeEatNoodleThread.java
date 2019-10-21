package top.youlanqiang.threadlean.book.pattern.singlethreadexecution.eatNoodle;

public class SafeEatNoodleThread extends Thread {

    private final String name;

    private final TablewarePair tablewarePair;

    public SafeEatNoodleThread(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    private void eat() {
        //tablewarePair是leftTool和rightTool的组合类
        //可以避免出现交叉锁的情况。
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + "(left)");

        }
    }
}
