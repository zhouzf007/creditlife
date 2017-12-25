package cn.zhangxd.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzf on 2017/12/4.
 */
@Configuration
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisClusterProperties {

    //集群节点
    private List<String> nodes=new ArrayList<>();

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
