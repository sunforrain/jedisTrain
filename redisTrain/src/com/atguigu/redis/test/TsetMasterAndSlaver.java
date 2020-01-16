package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;

// 27_尚硅谷__Redis_Jedis_主从复制
public class TsetMasterAndSlaver {
    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("192.168.13.128", 6379);
        Jedis jedis_S = new Jedis("192.168.13.128", 6380);// 别忘了linux开6380端口

        jedis_S.slaveof("192.168.13.128", 6379);

        jedis_M.set("class", "1122V2");
        // 因为内存太快,有时这里可能拿不到键和值
        System.out.println(jedis_S.get("class"));
    }
}
