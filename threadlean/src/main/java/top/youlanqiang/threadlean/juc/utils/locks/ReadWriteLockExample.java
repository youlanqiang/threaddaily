package top.youlanqiang.threadlean.juc.utils.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReadWriteLockExample {

    private static Map<String,Object> CACHE = new HashMap<>();
    private static ReadWriteLock rw = new ReentrantReadWriteLock();
    private static Lock r = rw.readLock();
    private static Lock w = rw.writeLock();

    public static Object get(String key){
        r.lock();
        try {
            return CACHE.get(key);
        }finally {
            r.unlock();
        }
    }

    /**
     * 返回之前的值
     * @param key
     * @param value
     * @return
     */
    public static Object put(String key, Object value){
        w.lock();
        try {
            return CACHE.put(key,value);
        }finally {
            w.unlock();
        }
    }

    public static void clear(){
        w.lock();
        try {
            CACHE.clear();
        }finally {
            w.unlock();
        }
    }
}
