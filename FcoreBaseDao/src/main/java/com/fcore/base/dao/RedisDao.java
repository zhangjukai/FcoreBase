package com.fcore.base.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository  
public class RedisDao {  
  
    @Autowired  
    RedisTemplate<Object,Object> redisTemplate;  
      
    @Resource(name="redisTemplate")  
    ValueOperations<Object,Object> valOps;  
      
    public void setValue(Object key,Object value){  
        valOps.set(key, value);
    }  
      
    public Object getValue(String id){ 
        return valOps.get(id);  
    }     
    
    public void test() {
    	redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return null;
			}
		});
    }
}  