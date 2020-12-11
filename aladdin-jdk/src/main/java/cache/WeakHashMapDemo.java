package cache;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put("name", "shixu");
        System.out.println(weakHashMap);


        String abc = new String("abc");
        WeakReference<String> abcWeakRef = new WeakReference<String>(abc);
        abc = null;
        System.out.println("before gc: " + abcWeakRef.get());
        System.gc();
        System.out.println("after gc: " + abcWeakRef.get());


    }
}
