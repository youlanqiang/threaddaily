package top.youlanqiang.threadlean.jmh;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// 基准测试类型，共有4种模式，可以配置多种不同类型的模式
@BenchmarkMode(Mode.AverageTime)
//统计结果输出时的单位
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
//@Measurement(iterations = 5) 等价于在Options中配置measurement参数
//@Warmup(iterations = 5)  等价于在Options中配置warmup参数， 这2个注解可以设置在方法上（要有@Benchmark注解）设置在方法上就是方法级配置
public class JMHExample01 {

    private final static String DATA = "DUMMY DATA";

    private List<String> arrayList;

    private List<String> linkedList;

    @Setup(Level.Iteration) //Setup：在每个基准测试批次前执行，注意是批次
    public void setUp(){
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
    }

    @TearDown(Level.Iteration) // tearDown: 每个基准测试批次后执行,注意是批次
    public void tearDown(){
        System.out.println("finish iteration");
    }

    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
    @Benchmark //@Benchmark注解类似junit中的@Test注解一样，是实际需要执行测试的方法
    @CompilerControl(CompilerControl.Mode.EXCLUDE) //禁止jvm优化该方法
    public List<String> arrayListAdd(){
        this.arrayList.add(DATA);
        return arrayList;
    }

    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
    @Benchmark
    public List<String> linkedListAdd(){
        this.linkedList.add(DATA);
        return linkedList;
    }

    public static void main(String[] args) throws RunnerException {
        final Options opt = new OptionsBuilder()
                .include(JMHExample01.class.getSimpleName())
                .forks(1)
                //.measurementIterations(10) //真正执行测试方法的代码次数，这个数据会添加到统计之中 ，这2个指标可以通过注解的形式来配置
                // 预热是指代码的执行已经经过了类的早期优化，JVM运行期编译和JIT优化之后的最终状态
                //.warmupIterations(10) //设置全局测试方法的代码预热次数，这个度量数据不会添加到统计之中
                .build();

        new Runner(opt).run();
    }

}
