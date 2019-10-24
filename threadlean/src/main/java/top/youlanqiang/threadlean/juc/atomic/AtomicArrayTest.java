package top.youlanqiang.threadlean.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayTest {

    public static void main(String[] args) {

        //初始化数组的大小
        AtomicIntegerArray integerArray = new AtomicIntegerArray(10);
        integerArray.set(0, 10);

        System.out.println(integerArray.get(0));


    }
}
