package top.youlanqiang.threadlean.book.twophase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache<K, V> {

    public static void main(String[] args) {
        LRUCache<String, Reference> cache = new LRUCache<>(
                5, key ->new Reference()
        );
        cache.get("Alex");
        cache.get("Jack");
        cache.get("Gavin");
        cache.get("Dillon");
        cache.get("Leo");
        //上面的数据在缓存中的新旧程度为Leo>Dillon>Gavin>Jack>Alex

        cache.get("Jenny"); //Alex将会被踢出
        System.out.println(cache.toString());
    }

    //用于记录key值的顺序
    private final LinkedList<K> keyList = new LinkedList<>();

    //用于存放数据
    private final Map<K, V> cache = new HashMap<>();

    //cache最大容量
    private final int capacity;

    private final CacheLoader<K, V> cacheLoader;

    public LRUCache(int capacity, CacheLoader<K, V> cacheLoader){
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value){
        //当元素数量超过容量时，将最老的数据清除
        if(keyList.size() >= capacity){
            K eldestKey = keyList.removeFirst();//eldest data
            cache.remove(eldestKey);
        }
        //如果数据已经存在，则从key的队列中删除
        if(keyList.contains(key)){
            keyList.remove(key);
        }
        //将key存放至队尾
        keyList.addLast(key);
        cache.put(key, value);
    }

    public V get(K key){
        V value;

        //先将key从 key list中删除
        boolean success = keyList.remove(key);
        if(!success){ //如果删除失效则表明该数据不存在
            //通过cacheloader对数据进行加载
            value  = cacheLoader.load(key);
            //调用put方法cache数据
            this.put(key, value);
        }else{
            //如果删除成功，则从cache中返回数据，并且将key再次放到队尾
            value = cache.get(key);
            keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }


}
