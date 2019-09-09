package com.demo.rule;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;

@Description(desc="小于")
public class Less  extends Rule{
	
	@RuleBase
	private Integer value;
	
	@RuleParam
	private Integer target;
	
	@Override
	public boolean excute() {
		return value.compareTo(target) > 0;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}
	
	
	
}
