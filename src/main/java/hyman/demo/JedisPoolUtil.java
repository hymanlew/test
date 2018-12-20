package hyman.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sun.swing.plaf.synth.DefaultSynthStyle;

/**
 * 声明双端解锁案例
 *
 * 1，获取 jedis 实例需要从 jedispool 中获取，
 * 2，用完 jedis 实例需要返还给 jedispool，
 * 3，如果 jedis 在使用中出错，则也需要还给 jedispool，
 */
public class JedisPoolUtil {

    // 实现高并发的 redis 连接池（只有一个），所以声明为静态的，方便调用。
    private static volatile JedisPool jedisPool = null;

    // 私有化构造方法，不允许 new。
    private JedisPoolUtil(){}

    public static JedisPool getJedisPoolInstance(){
        // 懒汉式单例模式，线程安全的。
        if(null == jedisPool){
            synchronized (JedisPoolUtil.class){
                if(null == jedisPool){
                    /**
                     * jedis 连接池的配置，初始化设置.
                     * jedisPool 的配置参数大部分是由 jedisPoolConfig 的对应项来赋值的。
                     *
                     * TestOnBorrow：是指获得一个 jedis实例的时候是否检查连接可用性（即测试 ping 命令），如果为 true，则
                     *              得到的 jedis实例均是可用的。
                     * TestOnReturn：return 一个 jedis实例给 pool时，是否检查连接可用性（即测试 ping 命令）。
                     */
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100*1000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(null,"127.0.0.1",6379);
                }
            }
        }
        return jedisPool;
    }

    // 参数分别是连接池，jedis 实例
    public static void release(JedisPool jedisPool, Jedis jedis){
        if(null != jedis){
            // 返回到 jedis连接池中
            jedisPool.returnResourceObject(jedis);
        }
    }
}
