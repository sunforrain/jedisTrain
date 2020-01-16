package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.JedisPoolUtil;

// 28_尚硅谷__Redis_Jedis_JedisPool
public class TestPool {
    public static void main(String[] args) {
        // 获取连接
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("aa", "bb");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最后要释放连接
            JedisPoolUtil.release(jedisPool, jedis);
        }

    }
}
