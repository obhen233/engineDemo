package com.demo.rule;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.Function;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;

@Function(name="euqal")
@Description(desc="equals",lang="en_us")
public class Equals extends Rule{

	
	@RuleBase
	private Integer value;
	
	@RuleParam
	private Integer target;
	
	@Override
	public boolean excute() {
		// TODO Auto-generated method stub
		return target.equals(value);
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
