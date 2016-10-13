package com.smartpants.artwork.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
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
@SuppressWarnings("unchecked")
public class PersonDaoJpa extends HibernateDaoSupport implements PersonDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Person getPerson(Long personId) throws DataAccessException {
		return (Person) entityManager.find(Person.class, personId);
	}

	public void savePerson(Person person) throws DataAccessException {
		entityManager.persist(person);
	}

	public Person authenticatePerson(String username, String password)
			throws DataAccessException, AuthenticationException {
		Query query = entityManager
				.createNativeQuery("select people from Person people where people.username = :username and people.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);		
		List<Person> validUsers = query.getResultList();
		if (validUsers == null || validUsers.size() <= 0)
			throw new AuthenticationException("No users found");
		return validUsers.get(0);
	}

	public List<Person> getPeople() {
		return entityManager.createQuery("select people from Person people").getResultList();
	}

	public Person getPersonByUsername(String username) throws EntityNotFoundException {
		Query query = entityManager.createNativeQuery("select people from Person people where people.username = :username");
		query.setParameter("username", username);
		List<Person> people = query.getResultList();
		if (people == null || people.size() <= 0)
			throw new EntityNotFoundException("Not person with username "
					+ username + " found");
		return people.get(0);
	}

}
