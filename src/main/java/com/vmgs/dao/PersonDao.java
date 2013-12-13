package com.vmgs.dao;

import java.util.List;

import com.vmgs.entity.Person;

public interface PersonDao {

	public Person save(Person person);
	
	public List<Person> findPersons();
	
}
