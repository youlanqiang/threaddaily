package top.youlanqiang.threadlean.book.workerthread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 流水线工人是Thread的子类
 * 不断地从流水线上提取产品然后进行再次加工
 * 加工的方法是create()
 */
public class Worker extends Thread{

    private final ProductionChannel channel;

    private final static Random random = new Random(System.currentTimeMillis());

    public Worker(String workerName, ProductionChannel channel){
        super(workerName);
        this.channel = channel;
    }

    @Override
    public void run(){
        while(true){
            try{
                //从传送带上获取产品
                Production production = channel.takeProduction();
                System.out.println(getName() + " process the" + production);

                //对产品进行加工
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
