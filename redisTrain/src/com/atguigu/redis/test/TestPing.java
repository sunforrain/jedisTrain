package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;
// redis 视频24, 测试连通性
public class TestPing {

    public static void main(String[] args) {
        // 测试用jedis连接redis,连接虚拟机的ip和对应端口号,注意防火墙要放开端口
        Jedis jedis = new Jedis("192.168.13.128",6379);
        System.out.println(jedis.ping());
    }

}
