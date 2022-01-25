package top.youlanqiang.threadlean.book.threadpool.train;

/**
 * @author youlanqiang
 * created in 2022/1/24 2:39 下午
 * 拒绝策略
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable);


    class AbortDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable) {
            // 直接无视请求
        }
    }

}
