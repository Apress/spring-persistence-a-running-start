package com.smartpants.artwork.dao.jpa;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.PersonDao;
import com.smartpants.artwork.domain.Person;
import com.smartpants.artwork.exception.AuthenticationException;
import com.smartpants.artwork.exception.EntityNotFoundException;

/**
 * Created by IntelliJ IDEA. Author: Paul T. Fisher User: paul Date: Feb 2, 2006
 * Time: 1:04:55 PM ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("personDao")
public class PersonDaoJpa extends GenericDaoJpa<Person> implements PersonDao {

   
	public PersonDaoJpa() {
       super(Person.class);
    }

    public Person authenticatePerson(String username, String password)
			throws DataAccessException, AuthenticationException {
		String queryStr = "select people from Person people where people.username = :username and people.password = :password";
        Query query = entityManager.createQuery(queryStr);
		query.setParameter("username", username);
		query.setParameter("password", password);		
		Person validUser = (Person) query.getSingleResult();
		if (validUser == null )
			throw new AuthenticationException("No users found");
		return validUser;
	}

	public Person getPersonByUsername(String username) throws EntityNotFoundException {
		Query query = entityManager.createQuery("select people from Person people where people.username = :username");
		query.setParameter("username", username);
		Person person = (Person) query.getSingleResult();
		if (person == null )
			throw new EntityNotFoundException("Not person with username "
					+ username + " found");
		return person;
	}

}
