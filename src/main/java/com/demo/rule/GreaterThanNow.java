package com.demo.rule;

import java.util.Date;

import com.github.obhen233.annotation.framework.DateFormat;
import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.NoBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;

@Description(desc="时间大于现在")
@NoBase
public class GreaterThanNow  extends Rule{

	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@RuleParam
	private Date target;

	@Override
	public boolean excute() {
		// TODO Auto-generated method stub
		return target.after(new Date());
	}

	public Date getTarget() {
		return target;
	}

	public void setTarget(Date target) {
		this.target = target;
	}
	
	
	
	
}
