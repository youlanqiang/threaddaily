package top.youlanqiang.threadlean.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerExample2 {

    public static void main(String[] args) {

        final Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(()->{
            Object aobj = new Object();
            System.out.println("A will send the object " + aobj);

            try {
                Object rObj = exchanger.exchange(aobj);
                System.out.println("A recieved the object "+ rObj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            Object bobj = new Object();
            System.out.println("B will send the object " + bobj);

            try {
                Object rObj = exchanger.exchange(bobj);
                System.out.println("A recieved the object "+ rObj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
