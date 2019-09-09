package com.demo.element;

import com.demo.rule.Great;
import com.demo.rule.IntBetween;
import com.github.obhen233.annotation.application.Leaf;
import com.github.obhen233.annotation.application.Rule;
import com.github.obhen233.element.Element;
import com.github.obhen233.result.ExcuteResult;
@Leaf({@Rule(rule=IntBetween.class),@Rule(rule=Great.class)})
public class NumberLeafOne  implements Element{

	@Override
	public ExcuteResult excute() {
		// TODO Auto-generated method stub
		return null;
	}
}
