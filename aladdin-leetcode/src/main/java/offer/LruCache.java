package offer;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache extends LinkedHashMap {
    int capacity;

    public LruCache(int capcity) {
        super();
        this.capacity = capcity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(5);
        lruCache.put("1", 1);
        lruCache.put("2", 2);
        lruCache.put("3", 3);
        lruCache.put("4", 4);
        System.out.println(lruCache);
        lruCache.put("5", 5);

        lruCache.put("6", 6);

        lruCache.put("2", 333);

        lruCache.put("7",7);

        System.out.println(lruCache);
    }
}


