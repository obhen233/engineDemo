package com.demo.rule;

import java.util.Date;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;

@Description(desc="时间介于")
public class DateBetween   extends Rule{

	@RuleBase
	private Date start;
	
	@RuleParam
	private Date target;
	
	@RuleBase
	private Date end;
	
	@Override
	public boolean excute() {
		return start.compareTo(target) < 0 && end.compareTo(target) > 0;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getTarget() {
		return target;
	}

	public void setTarget(Date target) {
		this.target = target;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	

}
