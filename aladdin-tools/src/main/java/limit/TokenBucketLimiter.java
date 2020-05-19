package limit;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketLimiter {


    Map<String, Entry> bucket = new HashMap<>();

    public static void main(String[] args) {
        TokenBucketLimiter tokenBucketLimiter = new TokenBucketLimiter();
        String resource = "resource";
        tokenBucketLimiter.setResource(resource, 20, 10);

        for (int i = 0; i < 2000000000; i++) {
            boolean ret = tokenBucketLimiter.acquire(resource);
            System.out.println(ret +"_"+ System.currentTimeMillis());
        }
    }

    public void setResource(String resource, int threshold, int windows) {
        Entry entry = new Entry(System.currentTimeMillis(), threshold, windows);
        bucket.put(resource, entry);
    }

    public boolean acquire(String resource) {

        Entry entry = bucket.get(resource);

        long now = System.currentTimeMillis();

        if (now - entry.getTimestamp() <= entry.getWindows()) {
            int tokenSize = entry.acquireToken();
            return tokenSize > 0;
        } else {
            entry.refill();
            return true;
        }
    }


    class Entry {
        long timestamp;
        int threshold;
        int windows;
        int tokenSize;

        public int acquireToken() {
            return tokenSize--;
        }

        public void refill() {
            tokenSize = threshold;
            timestamp = System.currentTimeMillis()+1;
        }

        public Entry(long timestamp, int threshold, int windows) {
            this.timestamp = timestamp;
            this.threshold = threshold;
            this.windows = windows;
            this.tokenSize = threshold;
        }

        public int getWindows() {
            return windows;
        }

        public void setWindows(int windows) {
            this.windows = windows;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getThreshold() {
            return threshold;
        }

        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }
    }

}
