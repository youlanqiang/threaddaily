package top.youlanqiang.threadlean.juc.atomic;

import java.util.stream.IntStream;

public class AtomicIntegerTest {

    /**
     * 保证内存可见，保证有序性
     */
    private  static volatile int value = 0;

    public static void main(String[] args) {
        IntStream.range(0, 2).forEach(x ->{
            new Thread(()->{
                int v = 0;
                while(v < 500){
                    int tmp = value;
                    System.out.println(Thread.currentThread().getName() + " : "+ tmp);
                    value += 1;
                    v++;
                }
            }).start();
        });
    }




}
