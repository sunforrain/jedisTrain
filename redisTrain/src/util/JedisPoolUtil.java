package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
// 28_尚硅谷__Redis_Jedis_JedisPool
// 一个单例类,用来获取jedis连接池
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil () {}

    public static JedisPool getJedisPoolInstance () {
        if(jedisPool == null) {
            synchronized (JedisPoolUtil.class)
            {
                JedisPoolConfig poolConfig = new JedisPoolConfig();
                poolConfig.setMaxActive(1000);
                poolConfig.setMaxIdle(32);
                poolConfig.setMaxWait(100*1000);
                poolConfig.setTestOnBorrow(true);


                jedisPool = new JedisPool(poolConfig,"192.168.13.128",6379);
            }
        }
        return jedisPool;
    }

    // 释放占用的连接,放回连接池
    public static void release(JedisPool jedisPool, Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResourceObject(jedis);
        }
    }
}
