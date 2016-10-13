package com.smartpants.artwork.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.smartpants.artwork.dao.PersonDao;
import com.smartpants.artwork.domain.Person;

/**
 * Created by IntelliJ IDEA. Author: Paul T. Fisher User: paul Date: Feb 2, 2006
 * Time: 1:01:25 AM ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dataModel.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class DomainModelTest {

	PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Test
	public void testPerson() {
		Person person = new Person();
		person.setFirstName("Paul");
		person.setLastName("Fisher");
		person.setUsername("pfisher");
		person.setPassword("password");
		person.setRoleLevel(Person.RoleLevel.ADMIN.getLevel());
		person.setVersion(1);
		personDao.savePerson(person);
		
		final List<Person> people = personDao.getPeople();
		Assert.assertEquals(1, people.size());
		
		
		try {
			Assert.assertEquals(people.get(0), personDao.authenticatePerson("pfisher", "password"));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		
		try{ 
			personDao.authenticatePerson("pfisher", "badpassword");
			Assert.fail("bad password should fail");
		} catch (Exception e) {
			//success
		}
	}
}
