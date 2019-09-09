package com.demo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.obhen233.out.FeildInfo;
import com.github.obhen233.out.FieldType;
import com.github.obhen233.out.RuleInfo;

public class RuleVo  implements Serializable {
	
	private String function;
	
	private String desc;
	
	private String expression;

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	public void setExpression(RuleInfo ruleInfo) {
		List<FeildInfo> feilds = ruleInfo.getFeilds();
		List<FeildInfo> paramInfo = new ArrayList<FeildInfo>();
		List<FeildInfo> baseInfo = new ArrayList<FeildInfo>();
		for(FeildInfo info:feilds){
			switch(info.getFieldType()){
				case base:
					baseInfo.add(info);break;
				case param:
					paramInfo.add(info);break;
			}
		}
		boolean hasParam = false;
		StringBuilder sb = new StringBuilder();
		sb.append("$");
		sb.append(ruleInfo.getFunction());
		sb.append("(");
		if(paramInfo.size()> 0){
			for(int i = 0;i<paramInfo.size();i++){
				FeildInfo param = paramInfo.get(i);
				if(!"param".equals(param.getSimpleName())){
					if(i!=0) sb.append(",");
					sb.append("#");
					sb.append(param.getSimpleName());
					sb.append("#");
				}else{
					hasParam = true;
				}
			}
		}
		if(baseInfo.size()> 0){
			for(int i = 0;i<baseInfo.size();i++){
				FeildInfo base = baseInfo.get(i);
				if(!"base".equals(base.getSimpleName())){
					if(i > 1 ||(paramInfo.size()> 0 && !hasParam))
						sb.append(",");
					sb.append("{");
					sb.append(base.getSimpleName());
					sb.append(":");
					if(base.getValue() != null && !"".equals(base.getValue()))
						sb.append(base.getValue());
					sb.append("}");
				}else{
					if(base.getValue() != null && !"".equals(base.getValue())){
						if(i > 1 ||(paramInfo.size()> 0 && !hasParam))
							sb.append(",");
						sb.append("{:");
						sb.append(base.getValue());
						sb.append("}");
					}
						
				}
				
			}
		}
		sb.append(")");
		this.expression = sb.toString();
	}
	
	
}
