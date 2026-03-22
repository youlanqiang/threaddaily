package top.youlanqiang.threadlean.jmh;


import org.apache.commons.math3.random.RandomGenerator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(5) //每个基准方法由5个线程执行
@State(Scope.Benchmark) //对象在整个基准测试中共享
public class JMHExampleParam {

    @Param({"1","2","3","4"}) //基准测试会将这4种类型都执行一遍
    private int type;

    private Map<String, String> map;

    @Setup
    public void setUp(){
        switch (type){
            case 1:
                map = new ConcurrentHashMap<>();
                break;
            case 2:
                map = new ConcurrentSkipListMap<>();
                break;
            case 3:
                map = Collections.synchronizedMap(new HashMap<>());
                break;
            case 4:
                map = new Hashtable<>();
                break;
        }
    }

    @Benchmark
    public void testPut(){
        map.put(new Random().nextInt(100)+"", String.valueOf(new Random().nextInt(100)));
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(JMHExampleParam.class.getName())
                .build()).run();
    }


}
