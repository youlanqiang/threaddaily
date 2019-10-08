package top.youlanqiang.threadlean.lesson15;


import java.util.concurrent.TimeUnit;

public class Test {


    public static void main(String[] args) {

        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>(){

            @Override
            public void onStart(Thread thread) {
                System.out.println("Thread is started");
            }

            @Override
            public void onRunning(Thread thread) {
                System.out.println("Thread is running");
            }

            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };

        Observable observableThread = new ObservableThread<>(lifecycle, ()->{
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("finished done.");
            return "Hello Observer";
        });

        observableThread.start();
    }
}
