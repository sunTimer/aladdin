package cache;

import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put("name","shixu");
        System.out.println(weakHashMap);
    }
}
