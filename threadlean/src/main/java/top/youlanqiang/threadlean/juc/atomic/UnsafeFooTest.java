package top.youlanqiang.threadlean.juc.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeFooTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Simple simple = Simple.class.newInstance();

        Unsafe unsafe = getUnsafe();
        Guard guard = new Guard();
        guard.work();
        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        //使用 unsafe给 guard内存赋值
        //  objectFieldOffset 是字段的内存地址偏移量
         unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42);
         guard.work();

    }


    static class Guard{
        private int ACCESS_ALLOWED = 1;

        private boolean allow(){
            return 42 == ACCESS_ALLOWED;
        }

        public void work(){
            if(allow()){
                System.out.println("i am working by allow");
            }
        }
    }

    static class Simple{
        private long l = 0;

        public Simple(){
            this.l = 1;
            System.out.println("=======");
        }

        public long get(){
            return this.l;
        }
    }

    //获取Unsafe对象
    public static Unsafe getUnsafe(){
        try {
            //Unsafe类内部有一个名为theUnsafe的私有实例变量，我们可以通过反射来获取该实例变量。
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
