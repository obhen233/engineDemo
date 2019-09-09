package com.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.plugin.RedisSynchStorePlugin;
import com.github.obhen233.producer.RootProducer;

public class RootInntListener  implements ServletContextListener{

	Logger logger = LoggerFactory.getLogger(RootInntListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//Properties properties = new Properties();
		//properties.setProperty("root-scan", "com.demo.root");
		try {
			//RootProducer.inntProducer(properties);
			logger.info("RootProducer innt.");
			RootProducer.inntProducer(new RedisSynchStorePlugin());
		} catch (Exception e) {
			logger.error("RootInntListener",e);
		}
	}

}
