package com.demo.element;

import com.demo.rule.IntInculde;
import com.github.obhen233.annotation.application.Leaf;
import com.github.obhen233.annotation.application.Rule;
import com.github.obhen233.element.Element;
import com.github.obhen233.result.ExcuteResult;

@Leaf(@Rule(rule=IntInculde.class,not=true))
public class NumberLeafTwo  implements Element{

	@Override
	public ExcuteResult excute() {
		// TODO Auto-generated method stub
		return null;
	}

}
