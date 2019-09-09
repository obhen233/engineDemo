package com.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.bean.RootVo;
import com.demo.bean.RuleVo;
import com.demo.util.JedisUtil;
import com.github.obhen233.element.Root;
import com.github.obhen233.engine.Engine;
import com.github.obhen233.out.RootParam;
import com.github.obhen233.out.RuleInfo;
import com.github.obhen233.producer.RootProducer;
import com.github.obhen233.producer.RuleProducer;
import com.github.obhen233.util.CodingUtil;
import com.github.obhen233.util.ExpressionUtil;
import com.github.obhen233.util.ObjectUtil;
import com.github.obhen233.util.RootKeyUtil;

import redis.clients.jedis.Jedis;

@Controller
public class DemoController {
	
	private Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	 @RequestMapping(value = { "index", "index.html", ""})
	public String index(){
		return "index";
	}
	 
	@RequestMapping(value="/toAddRoot")
	public String toAddRoot(){
		return "toadd";
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoots",produces = "application/json;charset=utf-8")
	public List<RootVo> getRoots(){
		return formatRootVo(RootProducer.getRoots());
	}
	
	@ResponseBody
	@RequestMapping(value="/getRuleInfo",produces = "application/json;charset=utf-8")
	public List<RuleVo> getRuleInfo(){
		try {
			List<RuleInfo> ruleinfos =  RuleProducer.getFeildInfo();
			return formatRuleVo(ruleinfos);
		} catch (Exception e) {
			logger.error("getRuleInfo",e);
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getRootsParam",produces = "application/json;charset=utf-8")
	public List<RootParam> getRootsParam(){
		try {
			return RootProducer.getRootsParam();
		} catch (Exception e) {
			logger.error("getRootsParam",e);
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/saveRoot",produces = "application/json;charset=utf-8")
	public Map saveRoot(String expression){
		Map resultMap = new HashMap();
		try {
			if(expression == null || "".equals(expression)){
				resultMap.put("flag", false);
				return resultMap;
			}
			Root root = ExpressionUtil.expression2Root(expression);
			root.setUnid(CodingUtil.getUUID());
			boolean saveFlag = RootProducer.insertRoot(root);
			
			resultMap.put("flag", saveFlag);
		} catch (Exception e) {
			logger.error("saveRoot",e);
			resultMap.put("flag", false);
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteRoot",produces = "application/json;charset=utf-8")
	public Map deleteRoot(String unid,String expression){
		Map resultMap = new HashMap();
		try {
			if(unid == null || "".equals(unid)){
				resultMap.put("flag", false);
				return resultMap;
			}
			if(expression == null || "".equals(expression)){
				resultMap.put("flag", false);
				return resultMap;
			}
			Root root = ExpressionUtil.expression2Root(expression);
			root.setUnid(unid);
			boolean deleteFlag = RootProducer.deleteRoot(root);
			resultMap.put("flag",deleteFlag);
		} catch (Exception e) {
			logger.error("deleteRoot",e);
			resultMap.put("flag", false);
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/getRootParam",produces = "application/json;charset=utf-8")
	public RootParam getRootParam(String unid){
		List<RootParam> paramList =  RootProducer.getRootParamByUnid(unid);
		if(paramList != null && paramList.size() > 0)
			return paramList.get(0);
		else
			return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/doExcute",produces = "application/json;charset=utf-8")
	public Map doExcute(String unid,HttpServletRequest request){
		Map paramMap = new HashMap();
		Map<String,String[]> params = request.getParameterMap();
		for(String key:params.keySet()){
			if("unid".equals(key))
				continue;
			String[] val = params.get(key);
			if(val.length == 1){//仅仅限定一个，方便写demo，需要限定多个，请自行定义。
				paramMap.put(key, val[0]);
			}
		}
		logger.info(params.toString());
		Object result;
		try {
			result = Engine.doExcute(paramMap, unid);
			logger.info("reulst is "+result);
			Map resultMap = new HashMap();
			resultMap.put("result", result);
			return resultMap;
		} catch (Exception e) {
			logger.error("doExcute",e);
			Map resultMap = new HashMap();
			resultMap.put("result", "doExcute error");
			return resultMap;
		}
	}
	
	@RequestMapping("/toexp")
	public String toexp(String unid,HttpServletRequest request){
		request.setAttribute("unid", unid);
		return "toexp";
	}
	
	private List<RuleVo> formatRuleVo(List<RuleInfo> ruleinfos){
		List<RuleVo> list = new ArrayList<RuleVo>();
		for(RuleInfo info:ruleinfos){
			RuleVo vo = new RuleVo();
			vo.setDesc(info.getDesc());
			vo.setFunction(info.getFunction());
			vo.setExpression(info);
			list.add(vo);
		}
		return list;
	}
	
	private List<RootVo> formatRootVo(List<Root> roots){
		List<RootVo> list = new ArrayList<RootVo>();
		for(Root root:roots){
			RootVo vo = new RootVo();
			vo.setUnid(root.getUnid());
			vo.setExpression(root);
			list.add(vo);
		}
		return list;
	}
	
	
	
}
