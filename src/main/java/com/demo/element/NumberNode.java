package com.demo.element;

import com.github.obhen233.annotation.application.Node;
import com.github.obhen233.element.Element;
import com.github.obhen233.result.ExcuteResult;

@Node(elements={NumberLeafOne.class,NumberLeafTwo.class})
public class NumberNode implements Element{

	@Override
	public ExcuteResult excute() {
		// TODO Auto-generated method stub
		return null;
	}

}
