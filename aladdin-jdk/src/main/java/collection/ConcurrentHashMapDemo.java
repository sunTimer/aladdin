package collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("shixu", "name");
        concurrentHashMap.put("name", "name");

        for (int i = 0; i < 10000; i++) {
            concurrentHashMap.put("" + i, i + "");
        }

        concurrentHashMap.get("name");
    }
}
