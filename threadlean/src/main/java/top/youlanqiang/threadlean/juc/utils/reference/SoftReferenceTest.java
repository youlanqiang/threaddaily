package top.youlanqiang.threadlean.juc.utils.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @author youlanqiang
 * @since 2022/11/27 13:02
 * 软引用测试，软引用的对象会在heap空间不足的时候直接被清空掉
 * -Xmx20m  -Xms20m
 */
public class SoftReferenceTest {


    public static void main(String[] args) throws InterruptedException {
        SoftReference<byte[]> soft = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(soft.get());

        TimeUnit.SECONDS.sleep(3);

        System.out.println(soft.get());

        TimeUnit.SECONDS.sleep(3);

        /**
         * jvm设置了20m的heap大小，所以在这里声明一个12m的byte数组，会将之前的软引用给gc掉
         */
        byte[] bytes = new byte[1024*1024*12];

        System.out.println(soft.get());
    }

}
