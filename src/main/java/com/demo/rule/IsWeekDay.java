package com.demo.rule;

import java.time.LocalDate;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.NoBase;
import com.github.obhen233.annotation.framework.NoParam;
import com.github.obhen233.attribute.Rule;

@Description(desc="是否是工作日,周一到周五")
@NoBase
@NoParam
public class IsWeekDay  extends Rule{

	@Override
	public boolean excute() {
		LocalDate today = LocalDate.now();
		int day = today.getDayOfWeek().getValue();
		return day < 6;
	}
	

}
