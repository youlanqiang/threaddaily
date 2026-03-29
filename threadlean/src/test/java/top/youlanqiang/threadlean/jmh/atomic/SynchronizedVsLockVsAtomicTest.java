package top.youlanqiang.threadlean.jmh.atomic;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试使用synchronized， Lock和AtomicInteger这3个类在进行原子性计算时的性能差距
 */
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(1)
@Timeout(time = 20, timeUnit = TimeUnit.SECONDS)
public class SynchronizedVsLockVsAtomicTest {

    @State(Scope.Group)
    public static class LockMonitor{
        private int x;
        private final Lock lock = new ReentrantLock();

        //使用lock来执行+1计算
        public void lockInc(){
            lock.lock();
            try{
                x++;
            }finally{
                lock.unlock();
            }
        }

        //使用synchronized来执行+1计算
        public void syncInc(){
            synchronized (this){
                x++;
            }
        }
    }

    @State(Scope.Group)
    public static class AtomicMonitor{
        private final AtomicInteger x = new AtomicInteger();

        public void inc(){
            x.incrementAndGet();
        }

    }

    @GroupThreads(10)
    @Group("sync")
    @Benchmark
    public void syncInc(LockMonitor lockMonitor){
        lockMonitor.syncInc();
    }

    @GroupThreads(10)
    @Group("lock")
    @Benchmark
    public void lockInc(LockMonitor lockMonitor){
        lockMonitor.lockInc();
    }

    @GroupThreads(10)
    @Group("atomic")
    @Benchmark
    public void atomicInc(AtomicMonitor monitor){
        monitor.inc();
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(SynchronizedVsLockVsAtomicTest.class.getSimpleName())
                .addProfiler(StackProfiler.class)
                .build()).run();
    }
}
