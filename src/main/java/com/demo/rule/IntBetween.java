package com.demo.rule;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;
@Description(desc="介于")
public class IntBetween  extends Rule{

	@RuleBase
	private Integer smail;
	
	@RuleBase
	private Integer big;
	
	@RuleParam
	private Integer target;
	
	@Override
	public boolean excute() {
		// TODO Auto-generated method stub
		return smail.compareTo(target) < 0 && big.compareTo(target) > 0;
	}

	public Integer getSmail() {
		return smail;
	}

	public void setSmail(Integer smail) {
		this.smail = smail;
	}

	public Integer getBig() {
		return big;
	}

	public void setBig(Integer big) {
		this.big = big;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}
	
	

}
