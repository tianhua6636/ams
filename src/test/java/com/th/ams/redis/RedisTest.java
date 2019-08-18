package com.th.ams.redis;

import com.th.ams.AmsApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author : wangtianhua
 * @date : 2019/08/18 8:59
 * @description : DOTO
 */
public class RedisTest extends AmsApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void contextLoads() {
		redisTemplate.opsForValue().set("test:set1", "testValue1");
		redisTemplate.opsForSet().add("test:set2", "asdf");
		redisTemplate.opsForHash().put("hash1", "name1", "lms1");
		redisTemplate.opsForHash().put("hash1", "name2", "lms2");
		redisTemplate.opsForHash().put("hash1", "name3", "lms3");
		System.out.println(redisTemplate.opsForValue().get("test:set1"));
		System.out.println(redisTemplate.opsForHash().get("hash1", "name1"));
	}
}
