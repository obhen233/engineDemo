package com.demo.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.obhen233.producer.RuleProducer;

public class RuleInntListener implements ServletContextListener{
	
	Logger logger = LoggerFactory.getLogger(RuleInntListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("RuleProducer innt.");
		Properties properties = new Properties();
		properties.setProperty("rule-scan", "com.demo.rule");
		try {
			RuleProducer.inntProducer(properties);
		} catch (Exception e) {
			logger.error("RuleInntListener",e);
		}
	}

}
