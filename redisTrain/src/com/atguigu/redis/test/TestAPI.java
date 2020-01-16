package com.atguigu.redis.test;

import redis.clients.jedis.Jedis;

import java.util.*;

// redis 25_Redis_Jedis_常用API
public class TestAPI {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.13.128",6379);
        // String相关
        // 向redis插入String
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        jedis.set("k4","v4");
        jedis.set("k5","v5");
        // 从redis获取String
        System.out.println(jedis.get("k1"));
        System.out.println(jedis.get("k2"));
        System.out.println(jedis.get("k6"));
        System.out.println("=============================");

        // mset和mget
        jedis.mset("str1","v1","str2","v2","str3","v3");
        System.out.println(jedis.mget("str1","str2","str3"));
        System.out.println("=============================");

        // 获取redis中的所有String
        Set<String> sets = jedis.keys("*");
        System.out.println(sets.size());
        // 对遍历器做循环
        for (Iterator it = sets.iterator();it.hasNext();){
            System.out.println(it.next());
        }
        System.out.println("=============================");

        // exist
        System.out.println("jedis.exist=======>"+ jedis.exists("k1"));
        System.out.println("=============================");

        // ttl
        System.out.println("jedis.ttl====>" + jedis.ttl("k1"));
        System.out.println("=============================");

        // list相关
        jedis.rpush("list1", "v1", "v2", "v3", "v4", "v5");
        List<String> list = jedis.lrange("list1",0, -1);
        for(String element : list) {
            System.out.println(element);
        }
        System.out.println("=============================");

        // set相关
        // 可以发现对于set,是可以持续加的
        jedis.sadd("orders", "jd001", "jd002");
        jedis.sadd("orders", "jd003");
        jedis.sadd("orderss", "jd002","jd003");
        Set<String> orders = jedis.smembers("orders");
        for (Iterator it = orders.iterator();it.hasNext();) {
            System.out.println(it.next());
        }

        jedis.srem("orders", "jd003");
        System.out.println(jedis.smembers("orders").size());

        Set<String> ordersDiff = jedis.sdiff("orders", "orderss");
        System.out.println(ordersDiff.size());
        System.out.println("=============================");

        // hash相关
        jedis.hset("hash1","userName", "lisi");
        System.out.println(jedis.hget("hash1","userName"));

        Map<String,String> map = new HashMap<String,String>();
        map.put("telphone","13811814763");
        map.put("address","atguigu");
        map.put("email","abc@163.com");
        jedis.hmset("hash2", map);
        List<String> hash2= jedis.hmget("hash2", "telphone", "address");
        for (String element : hash2) {
            System.out.println(element);
        }

        Set<String> keys = jedis.hkeys("hash2");
        for (Iterator it = keys.iterator();it.hasNext();) {
            System.out.println(it.next());
        }

        Map<String,String> hash2Map =jedis.hgetAll("hash2");
        System.out.println(hash2Map);
        System.out.println("=============================");

        //zset相关
        jedis.zadd("zset01",60d,"v1");
        jedis.zadd("zset01",70d,"v2");
        jedis.zadd("zset01",80d,"v3");
        jedis.zadd("zset01",90d,"v4");

        Set<String> s1 = jedis.zrange("zset01",0,-1);
        for (Iterator iterator = s1.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }

    }
}
