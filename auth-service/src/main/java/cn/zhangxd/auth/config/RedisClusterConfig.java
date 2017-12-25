package cn.zhangxd.auth.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhouzf on 2017/12/2.
 */
@Configuration
@ConditionalOnClass(OAuthConfiguration.class)
@EnableConfigurationProperties(RedisClusterProperties.class)
public class RedisClusterConfig {

    @Resource
    private RedisClusterProperties redisClusterProperties;

    // redis 单节点模式
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
//     redis Cluster 集群模式
//     error pipeline not supported
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        RedisClusterConfiguration clusterConfig=new RedisClusterConfiguration();
//        List<RedisNode> list=new ArrayList<>();
//        for (String node:redisClusterProperties.getNodes()){
//            String[] parts= StringUtils.split(node, ":");
//            RedisNode redisNode=new RedisNode(parts[0],Integer.valueOf(parts[1]));
//            list.add(redisNode);
//        }
//        clusterConfig.setClusterNodes(list);
//        return new JedisConnectionFactory(clusterConfig);
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    //redis Cluster 模式
    @Bean
    public JedisCluster redisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        for (String node : redisClusterProperties.getNodes()) {
            String[] parts = StringUtils.split(node, ":");
            Assert.state(parts.length == 2, "redis node shoule be defined as 'host:port', not '" + Arrays.toString(parts) + "'");
            nodes.add(new HostAndPort(parts[0], Integer.valueOf(parts[1])));
        }
        return new JedisCluster(nodes);
    }
}
