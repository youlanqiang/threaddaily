package top.youlanqiang.threadlean.juc.utils.reference;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author youlanqiang
 * @since 2022/11/27 13:10
 * 弱引用，会在没有其他强引用指向的时候直接被清空掉
 */
public class WeakReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        //使用string来做为reference的引用，会发现string无法被正常gc掉，这是因为jvm中字符串常量池
        //WeakReference<String> weakReference = new WeakReference<>("hello world");
        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024*1024*10]);
        System.out.println(weakReference.get());

        System.gc();
        TimeUnit.SECONDS.sleep(5);

        System.out.println(weakReference.get());

    }
}
