package middleware.org.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import middleware.org.config.PropertyValuesFromFile;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisConnectionPool {

	private static final Logger LOG = LogManager.getLogger(RedisConnectionPool.class);
	public static ShardedJedisPool shardedJedisPool = null;

    static {
        // Read these connection properties from property file
        boolean useSsl = true;
        String redisUrl = PropertyValuesFromFile.getInstance().getConfiguration("REDIS_URL");
        int redisPort = Integer.parseInt(PropertyValuesFromFile.getInstance().getConfiguration("REDIS_PORT"));
        String redisKey = PropertyValuesFromFile.getInstance().getConfiguration("REDIS_KEY");
        LOG.info("Redis Connection pool Parameter. redisUrl: {}"+redisUrl);
        LOG.info("Redis Connection pool Parameter. redisPort: {}"+ redisPort);
        LOG.info("Redis Connection pool Parameter. redisKey: {}"+redisKey);
        JedisShardInfo shardInfo = new JedisShardInfo(redisUrl, redisPort, useSsl);
        shardInfo.setPassword(redisKey);
        List<JedisShardInfo> shardInfoLst = new ArrayList<JedisShardInfo>();
        shardInfoLst.add(shardInfo);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(128);
        jedisPoolConfig.setMaxIdle(128);
        jedisPoolConfig.setMinIdle(16);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        jedisPoolConfig.setBlockWhenExhausted(true);
        shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, shardInfoLst);
        LOG.info("Redis Connection pool created.");
    }
}
