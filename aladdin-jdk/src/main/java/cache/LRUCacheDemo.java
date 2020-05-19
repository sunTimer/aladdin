package cache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(5);
        lruCache.put("name", "shixu");
        lruCache.put("address", "beijing");
        lruCache.put("age", "22");
        lruCache.put("hehe","22");
        lruCache.put("hehehe","234");
        lruCache.put("nenene","dfsfd");
        System.out.println(lruCache.storage);

        for (String s : lruCache) {
            System.out.println(s);
        }
    }
}
