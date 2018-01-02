package com.entrobus.credit.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouzf on 2017/12/27.
 */
public class CacheService {

    private final static Logger logger = LoggerFactory.getLogger(CacheService.class);

    /**
     * 缓存字符串内容
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setString(RedisTemplate redisTemplate, String key, String value) {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            //设置值
            valueOperations.set(key, value);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 缓存字符串内容并设置失效时间
     * expire 过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setStringExpir(RedisTemplate redisTemplate, String key, String value, long expire) {
        try {
            BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(key);
            //设置值
            boundValueOperations.set(value, expire, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }


    /**
     * 获取缓存的字符串内容
     *
     * @param key
     * @return
     */
    public static String getString(RedisTemplate redisTemplate, String key) {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String result = valueOperations.get(key);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * 获取缓存的字符串内容
     *
     * @param key
     * @return
     */
    public static Object getObject(RedisTemplate redisTemplate, String key) {
        try {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            Object result = valueOperations.get(key);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * 缓存对象
     *
     * @param objectKey
     * @param key
     * @param value
     * @return
     */
    public static boolean setEntity(RedisTemplate redisTemplate, Object objectKey, Object key, Object value) {
        try {
            HashOperations hashOperations = redisTemplate.opsForHash();
            //设置值
            hashOperations.put(objectKey, key, value);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据key保存为有序集合，排序：score
     *
     * @param key
     * @param obj
     * @param score
     * @return
     */
    public static boolean setZset(RedisTemplate redisTemplate, String key, Object obj, double score) {
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            //设置值
            zSetOperations.add(key, obj, score);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据key保存为无序集合
     *
     * @param key
     * @param obj
     * @return
     */
    public static boolean setNSet(RedisTemplate redisTemplate, String key, Object... obj) {
        Object[] object = obj.clone();
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            //设置值
            for (Object o : object) {
                setOperations.add(key, o);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据key保存为无序集合
     *
     * @param key
     * @return
     */
    public static long getSetSize(RedisTemplate redisTemplate, String key) {
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            return setOperations.size(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 获取zset 集合长度
     *
     * @param key
     * @return
     */
    public static long getZSetSize(RedisTemplate redisTemplate, String key) {
        try {
            ZSetOperations setOperations = redisTemplate.opsForZSet();
            return setOperations.size(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 压栈
     *
     * @param key
     * @param value
     * @return
     */
    public static Long listLeftPush(RedisTemplate redisTemplate, String key, Object value) {
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            //设置值
            return listOperations.leftPush(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 出栈
     *
     * @param key
     * @return
     */
    public static Object leftPop(RedisTemplate redisTemplate, String key) {
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            //设置值
            return listOperations.leftPop(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * List集合记录数
     *
     * @param key
     * @return
     */
    public static Long listLength(RedisTemplate redisTemplate, String key) {
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            //设置值
            return listOperations.size(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * 范围检索List
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<Object> getRangeList(RedisTemplate redisTemplate, String key, Long start, Long end) {
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            //设置值
            return listOperations.range(key, start, end);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 移除List成员
     *
     * @param key
     * @param i
     * @param value
     */
    public static void removeListMember(RedisTemplate redisTemplate, String key, long i, Object value) {
        try {
            ListOperations listOperations = redisTemplate.opsForList();
            //设置值
            listOperations.remove(key, i, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取有序集合，排序区间：start - end
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set getZset(RedisTemplate redisTemplate, String key, long start, long end) {
        try {
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            return zSetOperations.range(key, start, end);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取有序集合，分页
     *
     * @param key
     * @param page 0开始
     * @param size 分页大小
     * @return
     */
    public static Set getZsetPage(RedisTemplate redisTemplate, String key, int page, long size) {
        long start = page * size;
        return getZset(redisTemplate, key, start, start + size - 1);
    }

    /**
     * 根据key获取无序集合
     *
     * @param key
     * @return
     */
    public static Set getNset(RedisTemplate redisTemplate, String key) {
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            Set set = setOperations.members(key);
            return set;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * 根据key前缀获取所有key
     *
     * @param key
     * @return
     */
    public static Set<String> keys(RedisTemplate redisTemplate, String key) {
        try {
            Set<String> set = redisTemplate.keys(key + "*");
            return set;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据key前缀删除
     *
     * @param keyPrefix
     * @return
     */
    public static int deleteByKeyPrefix(RedisTemplate redisTemplate, String keyPrefix) {
        Set<String> set = keys(redisTemplate, keyPrefix);
        if (set != null && set.size() > 0) {
            bathDelete(redisTemplate, set);
            return set.size();
        }
        return 0;
    }


    /**
     * 获取缓存中的对象
     *
     * @param objectKey
     * @param key
     * @return
     */
    public static Object getEntity(RedisTemplate redisTemplate, Object objectKey, Object key) {
        try {
            HashOperations hashOperations = redisTemplate.opsForHash();
            Object o = hashOperations.get(objectKey, key);
            return hashOperations.get(objectKey, key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public static boolean delete(RedisTemplate redisTemplate, Serializable key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public static boolean delete(RedisTemplate redisTemplate, String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 批量删除缓存中得对象
     *
     * @param keys
     * @return
     */
    public static boolean bathDelete(RedisTemplate redisTemplate, Collection keys) {
        try {
            redisTemplate.delete(keys);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除集合中指定value
     *
     * @param val
     * @return
     */
    public static boolean removeFromSet(RedisTemplate redisTemplate, String key, Object val) {
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            setOperations.remove(key, val);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 缓存内容
     *
     * @param key
     * @param obj
     * @return
     */
    public static <T> boolean setCacheObj(RedisTemplate redisTemplate, String key, T obj) {
        try {
            ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
            //设置值
            valueOperations.set(key, obj);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }


    /**
     * 获取缓存的对象
     *
     * @param key
     * @return
     */
    public static <T> T getCacheObj(RedisTemplate redisTemplate, String key, Class<T> clazz) {
        try {
            return (T) redisTemplate.boundValueOps(key).get();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 缓存对象并设置失效时间
     *
     * @param key
     * @param obj
     * @param expire
     * @param <T>
     * @return
     */
    public static <T> boolean setCacheObjExpir(RedisTemplate redisTemplate, String key, T obj, long expire) {
        try {
            ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
            //设置值
            valueOperations.set(key, obj, expire, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }


    /**
     * 自增
     *
     * @param
     * @return
     */
    public static long getIncreaseNo(RedisTemplate redisTemplate, String key, long num) {
        try {
            long no = redisTemplate.opsForValue().increment(key, num);
            return no;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return 0;
        }
    }


    /**
     * 缓存非序列化数组
     *
     * @param
     * @param
     * @return
     */
    public static Boolean setByteValue(RedisTemplate redisTemplate, String k, byte[] value) {
        Boolean result = (Boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            RedisSerializer<String> redisSerializer = redisTemplate.getKeySerializer();
            byte[] key = redisSerializer.serialize(k);
            return redisConnection.setNX(key, value);
        });
        return result;
    }

    /**
     * 获取非序列化数组
     *
     * @param
     * @param
     * @return
     */
    public static byte[] getByteValue(RedisTemplate redisTemplate, String k) {
        byte[] v = (byte[]) redisTemplate.execute((RedisCallback<byte[]>) redisConnection -> {
            RedisSerializer<String> redisSerializer = redisTemplate.getKeySerializer();
            byte[] key = redisSerializer.serialize(k);
            return redisConnection.get(key);
        });
        return v;
    }

    public static long currtTimeFromRedis(RedisTemplate redisTemplate) { //获取redis当前时间
        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.time();
        });
    }


    /**
     * 添加元素到sorted set中
     *
     * @param key
     * @param obj
     * @param score
     * @return
     */
    public static <T> Boolean addSortedSet(RedisTemplate redisTemplate, String key, T obj, double score) {
        try {
            return redisTemplate.opsForZSet().add(key, obj, score);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 从sorted set中移除
     *
     * @param key
     * @param obj
     * @return
     */
    public static <T> void removeFromSortedSet(RedisTemplate redisTemplate, String key, T obj) {
        try {
            redisTemplate.opsForZSet().remove(key, obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 根据key 获取sorted set
     *
     * @param key
     * @param end
     * @param start
     * @return
     */
    public static Set getSortedSet(RedisTemplate redisTemplate, String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据key 获取sorted set
     *
     * @param key
     * @param end
     * @param start
     * @return
     */
    public static Set<ZSetOperations.TypedTuple<Object>> getSortedSetWithScore(RedisTemplate redisTemplate, String key, long start, long end) {
        try {
            Set<ZSetOperations.TypedTuple<Object>> set = redisTemplate.opsForZSet().rangeWithScores(key, start, end);
            return set;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据
     *
     * @return
     */
    public static void addToHash(RedisTemplate redisTemplate, String h, String hk) {
        try {
            redisTemplate.opsForHash().put(h, hk, 1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 根据
     *
     * @return
     */
    public static <T> T getHash(RedisTemplate redisTemplate, String h, String hk) {
        try {
            return (T) redisTemplate.opsForHash().get(h, hk);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 从hash 中移除
     *
     * @return
     */
    public static void removeFromHash(RedisTemplate redisTemplate, String h, String hk) {
        try {
            redisTemplate.opsForHash().delete(h, hk);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 是否存在该KEY
     *
     * @param key
     * @return
     */
    public static boolean checkKeyExist(RedisTemplate redisTemplate, String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

}
