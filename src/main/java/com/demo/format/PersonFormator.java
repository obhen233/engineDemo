package com.demo.format;

import com.demo.bean.Person;
import com.github.obhen233.formator.Formator;

public class PersonFormator implements Formator<Person>{

	@Override
	public String format(Person person) {
		return person.getId()+"+"+person.getName();
	}

	@Override
	public Person parse(String formatStr) {
		// TODO Auto-generated method stub
		Person person = new Person();
		if(formatStr == null || "".equals(formatStr))
			return null;
		String[] strs = formatStr.split("\\+");
		if(strs.length < 2)
			return null;
		person.setId(Integer.parseInt(strs[0]));
		person.setName(strs[1]);
		return person;
	}
	

	

}
