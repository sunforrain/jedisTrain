package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

// 26_尚硅谷__Redis_Jedis_事务,没有watch,简单示例
public class TestTXSimple {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.13.128",6379);
        Transaction transaction = jedis.multi();

        transaction.set("k4", "v4");
        transaction.set("k5", "v5");

//        transaction.exec();
        transaction.discard();
    }
}
