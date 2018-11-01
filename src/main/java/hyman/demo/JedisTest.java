package hyman.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JedisTest {

    public static void redisTest(){

        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        System.out.println("连接成功！");

        // 设置 redis 字符串数据
        jedis.set("hyman","testJava");
        System.out.println("hyman :"+jedis.get("hyman"));

        // 删除一个 key数据
        jedis.del("hyman");
        System.out.println("hyman :"+jedis.get("hyman"));

        // 获取使用命令存储的数据并输出
        System.out.println("set :"+jedis.smembers("set"));

        // 存储数据到列表中
        jedis.lpush("rlist","r1");
        jedis.lpush("rlist","r2");
        jedis.rpush("rlist","r3","r4");

        List<String> list = jedis.lrange("rlist",0,-1);
        for(String s:list){
            System.out.println(s);
        }
        System.out.println("========================");

        // 获取所有的 key数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    /**
     * 其他 jedis 的 demo，在另一个 demo 类中。
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

    public static void main(String[] args) {

        //redisTest();

        // 10W 数据直接读写操作的耗时：113545 毫秒！
        //noChannel();

        // 10W 数据使用通道操作的耗时：1663 毫秒！
        plusChanel();

        /**
         * 在本地运行的情况下性能差距就已经如此明显,更何况是在互联网项目当中,如果是频繁的操作Redis,使用管道技术去进行操作
         * 是可取的。不仅减少服务器压力,还能减少链路层中的时间消耗,批量处理频繁的操作,将大量操作结合成少量的操作，这是十分
         * 可取的。
         *
         * PipeLine的强大已经是非常直观的了。
         */
    }
}
