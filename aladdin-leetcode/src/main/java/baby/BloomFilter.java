package baby;

import com.google.common.hash.Hashing;
import org.junit.Assert;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * 布隆过滤器
 */
public class BloomFilter {

    private int capacity;
    private byte[] bitSets;
    private List<Hasher> hasherList;

    public BloomFilter(int capacity) {
        this.capacity = capacity;
        bitSets = new byte[capacity];
    }

    {
        int defaultHashSize = 5;
        hasherList = new ArrayList<>(defaultHashSize);
        hasherList.add(new Hasher(String::hashCode));
        hasherList.add(new Hasher(s -> Hashing.crc32().hashString(s, Charset.defaultCharset()).asInt()));
        hasherList.add(new Hasher(s -> Hashing.sipHash24().hashString(s, Charset.defaultCharset()).asInt()));
    }


    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(100000000);

        String _key = null;
        for (int i = 0; i < 10000; i++) {
            String key = UUID.randomUUID().toString();
            bloomFilter.put(key);
            if (i == 0) {
                _key = key;
            }
        }
        Assert.assertTrue(bloomFilter.get(_key));
        Assert.assertFalse(bloomFilter.get("no existed key"));
        Assert.assertFalse(bloomFilter.get("no existed key2"));
    }

    public void put(String key) {
        for (Hasher hasher : hasherList) {
            int hash = hasher.hash(key);
            bitSets[hash % capacity] = 1;
        }
    }

    public boolean get(String key) {
        int ret = 1;
        for (Hasher hasher : hasherList) {
            int hash = hasher.hash(key);
            ret = ret & bitSets[hash % capacity];
        }
        return ret == 1;
    }

    private static class Hasher {
        private Function<String, Integer> function;

        public Hasher(Function<String, Integer> function) {
            this.function = function;
        }

        public int hash(final String key) {
            return Math.abs(function.apply(key));
        }
    }
}
