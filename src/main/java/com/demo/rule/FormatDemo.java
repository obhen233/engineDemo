package com.demo.rule;

import java.util.List;

import com.demo.bean.Person;
import com.demo.format.PersonFormator;
import com.github.obhen233.annotation.framework.Description;
import com.github.obhen233.annotation.framework.Formator;
import com.github.obhen233.annotation.framework.Function;
import com.github.obhen233.annotation.framework.RuleBase;
import com.github.obhen233.annotation.framework.RuleParam;
import com.github.obhen233.attribute.Rule;

@Description(desc="这个是个Format的例子，包含人员")
@Function(name="personInculue")
public class FormatDemo  extends Rule{
	
	@RuleParam
	@Formator(PersonFormator.class)
	private Person person;
	
	@RuleBase
	private List<Integer> personIds;

	@Override
	public boolean excute() {
		if(person.getId() == null)
			return false;
		if(personIds == null || personIds.size() <= 0)
			return false;
		return personIds.contains(person.getId());
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Integer> getPersonIds() {
		return personIds;
	}

	public void setPersonIds(List<Integer> personIds) {
		this.personIds = personIds;
	}
	
	
	
	
}
