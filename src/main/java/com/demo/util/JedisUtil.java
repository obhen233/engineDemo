package com.demo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	
	    private static String HOST = "127.0.0.1";
	    
	    private static int PORT = 6379;
	    
	    private static String AUTH = "password";
	    
	    
	    private static JedisPool jedisPool = null;
	    
	    static {
	        try {
	           jedisPool = new JedisPool(HOST, PORT);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public synchronized static Jedis getJedis() {
	        try {
	            if (jedisPool != null) {
	                Jedis resource = jedisPool.getResource();
	                resource.auth(AUTH);
	                return resource;
	            } else {
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    public static void close(final Jedis jedis) {
	        if (jedis != null) {
	            jedis.close();
	        }
	    }
	  
}
