package top.youlanqiang.threadlean.book.pattern.readwritelock;

public class ReadWriteLockTest {

    private final static String text = "Thisisthreadexampleforreadwritelock";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);

        //创建两个线程进行数据写操作
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for(int index = 0; index < text.length(); index++){
                    try{
                        char c = text.charAt(index);
                        shareData.write(c);
                        System.out.println(Thread.currentThread() + " write " + c);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        //创建10个线程进行数据读操作
        for(int i = 0; i < 10; i++){
            new Thread(()->{
                while(true) {
                    try {
                        System.out.println(Thread.currentThread() + " read " + new String(shareData.read()));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
