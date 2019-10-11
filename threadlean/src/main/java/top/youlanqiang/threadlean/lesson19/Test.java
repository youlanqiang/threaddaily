package top.youlanqiang.threadlean.lesson19;

import java.util.concurrent.TimeUnit;

public class Test {


    public static void main(String[] args) throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();
        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finish done.");
        });
        //get方法会使当前线程进入阻塞
        future.get();

        FutureService<String, String> service2 = FutureService.newService();
        Future<String> future2 = service2.submit(in->{
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return in;
        }, "something");
        //get方法会使当前线程进入阻塞
        System.out.println(future2.get());


    }
}
