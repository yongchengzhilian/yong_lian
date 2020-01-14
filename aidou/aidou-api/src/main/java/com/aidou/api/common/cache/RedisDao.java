package com.aidou.api.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作hash
 * redisTemplate.opsForList();//操作list
 * redisTemplate.opsForSet();//操作set
 * redisTemplate.opsForZSet();//操作有序set
 */
@Repository
public class RedisDao {


    public Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate template;


    /**
     * 判断KEY是否存在
     *
     * @param key
     * @return
     */
    public boolean isExit(String key) {

        return template.hasKey(key);

    }

    public void delete(String key) {
        template.delete(key);

    }


    /**
     * 设置SET
     *
     * @param key
     * @param val
     */
    public void putSet(String key, String val) {
        SetOperations<String, String> opsForSet = template.opsForSet();
        opsForSet.add(key, val);
    }

    /**
     * 删除set
     *
     * @param key
     * @param val
     */
    public void delSet(String key, String val) {
        SetOperations<String, String> opsForSet = template.opsForSet();
        opsForSet.remove(key, val);
    }

    /**
     * 查找集合所有数据
     *
     * @param key
     * @return
     */
    public Set<String> findAllSet(String key) {
        SetOperations<String, String> opsForSet = template.opsForSet();
        Set<String> set = opsForSet.members(key);
        return set;
    }


    /*
    /向redis里存入数据和设置缓存时间  分
     */
    public void setKey(String key, String value, long minutes) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, minutes, TimeUnit.MINUTES);//1分钟过期
    }

    public void setKeyByMillisseconds(String key, String value, long minutes) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, minutes, TimeUnit.MILLISECONDS);//1分钟过期
    }


    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, 1, TimeUnit.MINUTES);//1分钟过期


    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

    public Optional<String> getValueByNull(String key) {
        String value = getValue(key);
        return Optional.ofNullable(value);
    }

    public void setList(String key, String val) {
        ListOperations<String, String> stringStringListOperations = this.template.opsForList();
        stringStringListOperations.leftPush(key, val);

    }

    public List<String> getList(String key) {
        ListOperations<String, String> stringStringListOperations = this.template.opsForList();
        List<String> range = stringStringListOperations.range(key, 0, -1);
        return range == null ? new ArrayList<>() : range;

    }

    public void setHash(String key, String atta, String val) {
        HashOperations<String, Object, Object> hashOperations = this.template.opsForHash();
        hashOperations.put(key, atta, val);

    }

    public void delHash(String key, String atta) {
        HashOperations<String, Object, Object> hashOperations = this.template.opsForHash();
        Long delete = hashOperations.delete(key, atta);

    }


    public String getHash(String key, String attr) {
        HashOperations<String, Object, Object> hashOperations = this.template.opsForHash();
        Object o = hashOperations.get(key, attr);
        if (o == null) {
            return "";
        }
        return o.toString();

    }

    public void setExpire(String key, long time) {
        this.template.expire(key, time, TimeUnit.MINUTES);
    }

    public Long getExpire(String key) {
        Long expire = this.template.getExpire(key, TimeUnit.MILLISECONDS);
        return expire;
    }


    public long getSetCount(String key) {
        System.out.println(key);
        SetOperations<String, String> opsForSet = template.opsForSet();
        Long size = opsForSet.size(key);
        return size == null ? 0 : size;
    }

    /**
     * 判断key 对应的val 是否存在
     *
     * @param key
     * @return
     */
    public Boolean getSetValExit(String key, String val) {
        SetOperations<String, String> opsForSet = template.opsForSet();
        return opsForSet.isMember(key, val);
    }
}
