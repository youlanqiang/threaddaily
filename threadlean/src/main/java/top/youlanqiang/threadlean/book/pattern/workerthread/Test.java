package top.youlanqiang.threadlean.book.workerthread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;

public class Test {

    public static void main(String[] args) {
        //流水线上有5个工人
        final ProductionChannel channel = new ProductionChannel(5);

        AtomicInteger productionNo = new AtomicInteger();

        IntStream.range(1, 8).forEach(i ->{
            new Thread(()->{
                while(true){
                    channel.offerProduction(new Production(productionNo.getAndIncrement()));

                    try{
                        TimeUnit.SECONDS.sleep(current().nextInt(10));
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }
}
