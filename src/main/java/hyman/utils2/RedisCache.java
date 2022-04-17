package hyman.utils2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    public RedisCache() {
    }

    public Integer getModelId() {
        Integer modelId = SecurityUtils.getModelId();
        if (modelId == null) {
            System.out.println("redis缓存中没有找到modelId");
            return null;
        } else {
            System.out.println("modelId from cache =" + modelId);
            return modelId;
        }
    }

    public Integer getWorkingId() {
        Integer workingId = SecurityUtils.getWorkingId();
        if (workingId == null) {
            System.out.println("redis缓存中没有找到workingId");
            return null;
        } else {
            System.out.println("workingId from cache =" + workingId);
            return workingId;
        }
    }

    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operation = this.redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, T> operation = this.redisTemplate.opsForValue();
        operation.set(key, value, (long) timeout, timeUnit);
        return operation;
    }

    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = this.redisTemplate.opsForValue();
        return operation.get(key);
    }

    public void deleteObject(String key) {
        this.redisTemplate.delete(key);
    }

    public void deleteObject(Collection collection) {
        this.redisTemplate.delete(collection);
    }

    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations listOperation = this.redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();

            for (int i = 0; i < size; ++i) {
                listOperation.leftPush(key, dataList.get(i));
            }
        }

        return listOperation;
    }

    public <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList();
        ListOperations<String, T> listOperation = this.redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; (long) i < size; ++i) {
            dataList.add(listOperation.index(key, (long) i));
        }

        return dataList;
    }

    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = this.redisTemplate.boundSetOps(key);
        Iterator it = dataSet.iterator();

        while (it.hasNext()) {
            setOperation.add(new Object[]{it.next()});
        }

        return setOperation;
    }

    public <T> Set<T> getCacheSet(String key) {
        new HashSet();
        BoundSetOperations<String, T> operation = this.redisTemplate.boundSetOps(key);
        Set<T> dataSet = operation.members();
        return dataSet;
    }

    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = this.redisTemplate.opsForHash();
        if (null != dataMap) {
            Iterator var4 = dataMap.entrySet().iterator();

            while (var4.hasNext()) {
                Entry<String, T> entry = (Entry) var4.next();
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }

        return hashOperations;
    }

    public <T> Map<String, T> getCacheMap(String key) {
        Map<String, T> map = this.redisTemplate.opsForHash().entries(key);
        return map;
    }

    public Collection<String> keys(String pattern) {
        return this.redisTemplate.keys(pattern);
    }
}

