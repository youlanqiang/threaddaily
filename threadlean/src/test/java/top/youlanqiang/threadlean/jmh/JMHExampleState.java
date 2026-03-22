package top.youlanqiang.threadlean.jmh;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5,  time = 1, timeUnit = TimeUnit.SECONDS)
//设置5个线程运行基准测试用例
@Threads(5)
public class JMHExampleState {

    //JMH中有很多种State，其中Thread表示执行测试的运行线程，每个线程都会持有一个Test的实力，主要用来测试非线程安全的类,threads=5
    //构造函数中的create instance被打印了5次
    @State(Scope.Thread)
    //Scope.Benchmark表示测试都是共用一个实例的，常用来测试线程安全的类
    //@State(Scope.Benchmark)
    public static class Test{

        public Test(){
            System.out.println("create instance");
        }

        public void method(){}

    }

    @Benchmark
    public void test(Test test){
        test.method();
    }


    public static void main(String[] args) throws RunnerException {
       new Runner(new OptionsBuilder()
               .include(JMHExampleState.class.getName())
               .build()).run();
    }

}
