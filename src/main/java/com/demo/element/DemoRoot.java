package com.demo.element;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.obhen233.annotation.application.Node;
import com.github.obhen233.annotation.application.Root;
import com.github.obhen233.element.Bud;
import com.github.obhen233.producer.RuleProducer;
import com.github.obhen233.util.ExpressionUtil;
@Root(unid="demo_No1")
@Node(elements = {NumberNode.class,DateNode.class,Bud.class})
public class DemoRoot{
	private static Logger logger = LoggerFactory.getLogger(DemoRoot.class);
	static{
		Properties properties = new Properties();
		properties.setProperty("rule-scan", "com.demo.rule");
		try {
			RuleProducer.inntProducer(properties);
		} catch (Exception e) {
			logger.error("innt RuleProducer",e);
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println(ExpressionUtil.expression2Root(DemoRoot.class));
	}
}
