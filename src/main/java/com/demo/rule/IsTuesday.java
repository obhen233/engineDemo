package com.demo.rule;

import java.time.LocalDate;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.NoBase;
import com.github.obhen233.annotation.framework.NoParam;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.attribute.Rule;

@Description(desc="周二会员日")
@NoParam
public class IsTuesday  extends Rule{

	@RuleBase(base="2")
	private int memberDay;
	
	@Override
	public boolean excute() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		int day = today.getDayOfWeek().getValue();
		return day == memberDay;
	}

	public int getMemberDay() {
		return memberDay;
	}

	public void setMemberDay(int memberDay) {
		this.memberDay = memberDay;
	}
	
	
	
}
