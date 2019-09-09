package com.demo.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.util.JedisUtil;
import com.github.obhen233.element.Root;
import com.github.obhen233.plugin.SynchStorePlugin;
import com.github.obhen233.producer.RootProducer;
import com.github.obhen233.util.ObjectUtil;

import redis.clients.jedis.Jedis;


public class RedisSynchStorePlugin extends SynchStorePlugin{
	Logger logger = LoggerFactory.getLogger(RedisSynchStorePlugin.class);
	public static String expressionin_hash = "purslane_hash";
	@Override
	public boolean deleteRoot(Root root) {
		try {
			String key = RootProducer.getKeyStrFromRoot(root);
			Jedis jedis = JedisUtil.getJedis();
			Long i = jedis.hdel(expressionin_hash, key);
			JedisUtil.close(jedis);
			return i > 0;
		} catch (Exception e) {
			logger.error("RedisSynchStorePlugin#deleteRoot",e);
			return false;
		}
		
	}

	@Override
	public List<Root> getRoots() {
		Jedis jedis = JedisUtil.getJedis();
		Map<byte[], byte[]> bytesMap = jedis.hgetAll(expressionin_hash.getBytes());
		if(bytesMap == null)
			return null;
		List<Root> list = new ArrayList<Root>(bytesMap.size());
		for(byte[] bk:bytesMap.keySet()){
			list.add((Root)ObjectUtil.toObject(bytesMap.get(bk)));
		}
		JedisUtil.close(jedis);
		return list;
	}

	@Override
	public boolean saveRoot(Root root, boolean updateflag) {
		try {
			String key = RootProducer.getKeyStrFromRoot(root);
			Jedis jedis = JedisUtil.getJedis();
			if(!updateflag){
				if(jedis.hexists(expressionin_hash, key))
					return true;
			}
			Long i = jedis.hset(expressionin_hash.getBytes(), key.getBytes(), ObjectUtil.toByteArray(root));
			JedisUtil.close(jedis);
			return i > 0;
		} catch (Exception e) {
			logger.error("RedisSynchStorePlugin#deleteRoot",e);
			return false;
		}
	}

}
