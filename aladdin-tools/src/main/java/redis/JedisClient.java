package redis;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


        sortedSet();
    }

    public static void sortedSet() {
        jedis.zadd("name", 33, "shixu");
        jedis.zadd("name", 1, "wanger");
        jedis.zadd("name", 4, "zhangsan");

        Set<String> nameSets = jedis.zrange("name", 0, -1);
        System.out.println(nameSets);
    }
}
