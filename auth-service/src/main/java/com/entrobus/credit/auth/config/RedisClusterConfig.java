package com.entrobus.credit.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by zhouzf on 2017/12/2.
 */
@Configuration
@ConditionalOnClass(OAuthConfiguration.class)
public class RedisClusterConfig {

//    @Resource
//    private RedisClusterProperties redisClusterProperties;

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

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> templates = new RedisTemplate();
//        templates.setConnectionFactory(factory);
//        templates.setKeySerializer(new StringRedisSerializer());
//        templates.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        templates.setHashKeySerializer(new StringRedisSerializer());
//        templates.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return templates;
//    }

    //redis Cluster 模式
//    @Bean
//    public JedisCluster redisCluster() {
//        Set<HostAndPort> nodes = new HashSet<>();
//        for (String node : redisClusterProperties.getNodes()) {
//            String[] parts = StringUtils.split(node, ":");
//            Assert.state(parts.length == 2, "redis node shoule be defined as 'host:port', not '" + Arrays.toString(parts) + "'");
//            nodes.add(new HostAndPort(parts[0], Integer.valueOf(parts[1])));
//        }
//        return new JedisCluster(nodes);
//    }
}
