package com.demo.rule;

import java.util.Date;

import com.github.obhen233.annotation.framework.DateFormat;
import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;

@Description(desc="时间大于")
public class DateGreat  extends Rule{
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@RuleBase
	private Date value;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@RuleParam
	private Date target;
	
	@Override
	public boolean excute() {
		return value.compareTo(target) > 0;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public Date getTarget() {
		return target;
	}

	public void setTarget(Date target) {
		this.target = target;
	}
	
	
}
