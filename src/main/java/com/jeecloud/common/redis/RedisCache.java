package com.jeecloud.common.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import com.jeecloud.common.util.SpringContextUtil;

/**
 * @ClassName: RedisCache
 * @Description: redis缓存
 * @author: admin
 * @date: 2018年3月15日 上午11:16:45
 */
public class RedisCache implements Cache {
	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
	// 读写锁
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
	private RedisTemplate<String, Object> redisTemplate;
	private String id;

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.info("Redis Cache id " + id);
		this.id = id;
	}
	@Override
	public String getId() {
		return this.id;
	}
	@Override
	public void putObject(Object key, Object value) {
		if (value != null) {
			// 向Redis中添加数据，有效时间是2天
			getRedisTemplate().opsForValue().set(key.toString(), value, 2, TimeUnit.DAYS);
		}
	}
	@Override
	public Object getObject(Object key) {
		try {
			if (key != null) {
				Object obj = getRedisTemplate().opsForValue().get(key.toString());
				return obj;
			}
		} catch (Exception e) {
			logger.error("redis ");
		}
		return null;
	}
	@Override
	public Object removeObject(Object key) {
		try {
			if (key != null) {
				getRedisTemplate().delete(key.toString());
			}
		} catch (Exception e) {
		}
		return null;
	}
	@Override
	public void clear() {
		logger.debug("清空缓存");
		try {
			Set<String> keys = getRedisTemplate().keys("*:" + this.id + "*");
			if (!CollectionUtils.isEmpty(keys)) {
				redisTemplate.delete(keys);
			}
		} catch (Exception e) {
		}
	}
	@Override
	public int getSize() {
		Long size = (Long) getRedisTemplate().execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});
		return size.intValue();
	}
	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}
	private RedisTemplate<String, Object> getRedisTemplate() {
		if (redisTemplate == null) {
			redisTemplate = SpringContextUtil.getBean("redisTemplate");
		}
		return redisTemplate;
	}
}
