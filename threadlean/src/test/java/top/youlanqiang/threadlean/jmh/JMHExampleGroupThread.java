package top.youlanqiang.threadlean.jmh;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 测试多线程执行基准测试
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class JMHExampleGroupThread {

    @State(Scope.Group)
    public static class Test{

        public Test(){
            System.out.println("create instance");
        }

        public void write(){
            System.out.println("write");
        }

        public void read(){
            System.out.println("read");
        }
    }

    //在线程组test中，有3个线程会不断执行测试方法
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testWrite(Test test){
        test.write();
    }


    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testRead(Test test){
        test.read();
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder().include(JMHExampleGroupThread.class.getSimpleName()).build()).run();
    }

}
