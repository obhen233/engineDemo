package com.demo.element;

import com.demo.rule.DateBetween;
import com.github.obhen233.annotation.application.Leaf;
import com.github.obhen233.annotation.application.Rule;
import com.github.obhen233.element.Element;
import com.github.obhen233.result.ExcuteResult;

@Leaf(@Rule(rule=DateBetween.class))
public class DateLeafOne  implements Element{

	@Override
	public ExcuteResult excute() {
		// TODO Auto-generated method stub
		return null;
	}

}
