package com.demo.rule;

import java.util.ArrayList;
import java.util.List;

import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;

@Description(desc="包含")
public class IntInculde   extends Rule{
	
	@RuleParam
	private Integer target;
	
	@RuleBase
	private List<Integer> bases;

	@Override
	public boolean excute() {
		// TODO Auto-generated method stub
		System.out.println(target);
		System.out.println(bases);
		System.out.println(bases.size());
		for(Object o :bases){
			System.out.println(o.getClass());
		}
		return bases.contains(target);
	}
	

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public List<Integer> getBases() {
		return bases;
	}

	public void setBases(List<Integer> bases) {
		this.bases = bases;
	}
	
	
}
