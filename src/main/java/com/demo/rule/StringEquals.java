package com.demo.rule;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.Function;
import com.github.obhen233.attribute.Rule;

@Function(name="strEuqal")
@Description(desc="字符串的比较,用默认值")
public class StringEquals  extends Rule{

	@Override
	public boolean excute() {
		return getParam().equals(getBase());
	}
	
}
