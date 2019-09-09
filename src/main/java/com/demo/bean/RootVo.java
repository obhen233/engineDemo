package com.demo.bean;

import java.io.Serializable;

import com.github.obhen233.element.Root;

public class RootVo implements Serializable {
	
	private String unid;
	
	private String expression;

	public String getUnid() {
		return unid;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public void setExpression(Root root) {
		this.expression = root.toString();
	}
}
