package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JedisClient {

    private static Jedis jedis = new Jedis();

    public static void main(String[] args) {
        List<Jedis> jedisList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Jedis jedis = new Jedis();
            jedis.lpush("names", "shixu");
            jedis.lpush(" names", "nafang");
            List<String> name1 = jedis.lrange("names", 0, -1);
            System.out.println(name1);
        }
        // 过期时间
        sortedSet();
    }

    public static void sortedSet() {
        jedis.zadd("name", 33, "shixu");
        jedis.zadd("name", 1, "wanger");
        jedis.zadd("name", 4, "zhangsan");

        Set<String> nameSets = jedis.zrange("name", 0, -1);
        System.out.println(nameSets);
    }

    @Test
    public void testForRedisClientLimit() throws InterruptedException {
        int n = 1;
        final List<Jedis> jedisList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < n; i++) {
            final int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = new Jedis();
                    System.out.println("client " + finalI);
                    jedis.get("name");
                    jedisList.add(jedis);
                }
            });
        }

        System.out.println(jedisList.size());
        Thread.sleep(100000);
    }
}
