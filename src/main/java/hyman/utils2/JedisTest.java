package hyman.utils2;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import java.util.*;


import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class JedisTest {

    public static void redisTest(){

        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        System.out.println(jedis.ping());
        System.out.println("连接成功！");

        // key
        // 获取所有的 key数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("连接成功！");

        // 设置 redis 字符串数据
        jedis.set("hyman","testJava");
        System.out.println("hyman :"+jedis.get("hyman"));


        // 查看指定的 key 是否存在。以及剩余的生存时间，-1 表示永不过期，-2 表示已过期。
        System.out.println("hyman exists"+jedis.exists("hyman"));
        System.out.println(jedis.ttl("hyman"));

        // 删除一个 key数据
        jedis.del("hyman");
        System.out.println("hyman :"+jedis.get("hyman"));

        // mset，mget
        jedis.mset("s1","v1","s2","v2");
        System.out.println(jedis.mget("s1","s2","s3"));


        // list
        // 获取使用命令存储的数据并输出
        System.out.println("set :"+jedis.smembers("set"));

        // 存储数据到列表中
        jedis.lpush("rlist","r1");
        jedis.lpush("rlist","r2");
        jedis.rpush("rlist","r3","r4");

        // 获取 list 中所有的数据
        List<String> list = jedis.lrange("rlist",0,-1);
        for(String s:list){
            System.out.println(s);
        }
        System.out.println("========================");


        // set
        jedis.sadd("order","001");
        jedis.sadd("order","002");
        // 获取使用命令存储的数据并输出
        Set<String> sets = jedis.smembers("order");
        for(Iterator ir=sets.iterator(); ir.hasNext();){
            String string = (String)ir.next();
            System.out.println(string);
        }
        // 从 set 中移除
        jedis.srem("order","002");


        // hash
        jedis.hset("user","name","lili");
        System.out.println(jedis.hget("user","name"));
        Map<String,String> map = new HashMap<>();
        map.put("phone","123456");
        map.put("address","henan");
        // 批量存储
        jedis.hmset("user",map);
        List<String> result = jedis.hmget("user","name","phone");
        for(String s : result){
            System.out.println(s);
        }


        // zset，存储一个 double 类型的分数
        jedis.zadd("zset",10d,"v1");
        jedis.zadd("zset",20d,"v2");
        jedis.zadd("zset",30d,"v3");
        // 查询出 set 所有的元素
        Set<String> stringSet = jedis.zrange("zset",0,-1);
        for(Iterator ir=stringSet.iterator(); ir.hasNext();){
            String s = (String) ir.next();
            System.out.println(s);
        }
    }

    /**
     * redis 事务
     */
    public static void transactionTest(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        // 开启事务
        Transaction transaction = jedis.multi();
        transaction.set("tr1","v1");
        transaction.set("tr2","v2");

        // 执行事务，或者是取消事务
        //transaction.exec();
        transaction.discard();
    }

    /**
     * redis 事务监控
     * 通俗点讲，watch 命令就是标记一个键，如果标记了一个键，在提交事务前如果该键被别人修改过，则事务就会失败而不执行。这种
     * 情况通常会在程序中取消 watch 监控，然后再开启监控重新再执行一次。
     * 以下是，首先标记了键 balance，然后检查余额是否足够，不足就取消标记，且不做扣减。如果足够的话，则就启动事务进行扣减。
     * 如果在此期间键 balance 被其他人修改，那么提交事务（执行 exec）时就会报错，程序中通常可以捕获这类错误，并且会再重新执
     * 行一次，直到成功。
     */
    public boolean transWatch(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        jedis.set("balance","100");
        jedis.set("debt","0");

        // 可用余额
        int balance;
        // 欠额
        int debt;
        // 取款金额
        int ATMtake = 20;

        jedis.watch("balance");
        // 模拟高并发，或者网络延迟时的阻塞情况
        try {
            // 模拟其他人已经更改过了可用余额
            //jedis.set("balance","50");
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance = Integer.parseInt(jedis.get("balance"));
        if(balance < ATMtake){
            jedis.unwatch();
            System.out.println("余额已经变动过了！");
            return false;
        }else{
            System.out.println("执行事务，开始取钱！");
            Transaction transaction = jedis.multi();
            // 减小指定的数值
            transaction.decrBy("balance",ATMtake);
            transaction.incrBy("debt",ATMtake);
            transaction.exec();

            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));
            System.out.println(balance +", "+ debt);
            return true;
        }
    }

    /**
        // 获取所有的 key数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    /**
     * 其他 jedis 的 utils2，在另一个 utils2 类中。
     *
     * Redis 的管道（Pipeline）可以在大量数据需要一次性操作完成的时候,使用Pipeline进行批处理,将一大队的操作合并成一次操作,可以减少链路层
     * 的时间消耗,毕竟频繁操作是不好的嘛.
     */
    public static void noChannel(){

        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        long start = System.currentTimeMillis();

        // 批量执行一些操作
        for(int i=0;i<100000;i++){
            jedis.set("testKey"+String.valueOf(i),"value"+String.valueOf(i));
            jedis.get("testKey"+String.valueOf(i));
        }

        long end = System.currentTimeMillis();
        System.out.println("10W 数据直接读写操作的耗时："+(end-start)+" 毫秒！");

    }

    public static void plusChanel(){

        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();

        // 批量执行一些操作
        for(int i=0;i<100000;i++){
            pipeline.set("testKey"+String.valueOf(i),"value"+String.valueOf(i));
            pipeline.get("testKey"+String.valueOf(i));
        }

        long end = System.currentTimeMillis();
        System.out.println("10W 数据使用通道操作的耗时："+(end-start)+" 毫秒！");
    }

    public static void masterSlave(){
        Jedis master = new Jedis("127.0.0.1",6379);
        Jedis slave = new Jedis("127.0.0.1",6380);

        // 配从不配主
        slave.slaveof("127.0.0.1",6379);
        master.set("master","222");
        String result = slave.get("master");
        System.out.println(result);

        /**
         * 但有时候直接执行会有一个小问题，就是 result 的结果可能是 null空值。
         * 这是因为 redis 是内存数据库，数据都是先存在于内存中，而代码在执行时，缓存里还没有数据，所以就返回空值。
         * 也是针对于第一次存入的 key，如果之前已经有了 key键值，则内存缓存中就会有，而不会出现此问题。
         *
         * 它不是 bug，而是内存相对于缓存太快了，多执行一次即可。
         *
         * 另外在程序中，也是很少直接使用代码来确定主从库，而是直接在架构中确定好了的。在程序中主要关心的是谁主谁从，即
         * 主写从读。
         */
    }

    public static void jedisPooltest(){
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        JedisPool jedisPool1 = JedisPoolUtil.getJedisPoolInstance();
        // 因为是单例模式，所以只会有一个实例，输出为 true
        System.out.println(jedisPool == jedisPool1);

        Jedis jedis = null;
        try {
            // 从连接池中拿到 jedis 连接实例
            jedis = jedisPool.getResource();
            jedis.set("pool","p1");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool,jedis);
        }
    }

    public static void main(String[] args) {

        //redisTest();

        // 10W 数据直接读写操作的耗时：113545 毫秒！
        //noChannel();

        // 10W 数据使用通道操作的耗时：1663 毫秒！
        //plusChanel();


        /**
         * 在本地运行的情况下性能差距就已经如此明显,更何况是在互联网项目当中,如果是频繁的操作Redis,使用管道技术去进行操作
         * 是可取的。不仅减少服务器压力,还能减少链路层中的时间消耗,批量处理频繁的操作,将大量操作结合成少量的操作，这是十分
         * 可取的。
         *
         * PipeLine的强大已经是非常直观的了。
         */

        //JedisTest test = new JedisTest();
        //boolean check = test.transWatch();
        //System.out.println(check);
        //if(!check){
        //    test.transWatch();
        //}
    }
}
