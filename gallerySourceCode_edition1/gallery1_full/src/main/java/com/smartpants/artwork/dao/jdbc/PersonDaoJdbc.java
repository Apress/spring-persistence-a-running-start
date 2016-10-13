package com.smartpants.artwork.dao.jdbc;

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
public class PersonDaoJdbc extends GenericDaoJdbc<Person> implements PersonDao {
   
	public PersonDaoJdbc() {
       super(Person.class, "person");
    }	

    public Person authenticatePerson(String username, String password)
			throws DataAccessException, AuthenticationException {
		String queryStr = "select * from person people where people.username = ? and people.password = ?";
	    Person result = getSimpleJdbcTemplate().queryForObject(queryStr, getRowMapper(), username, password);
		if (result == null)
			throw new AuthenticationException("No users found");
		return result;
	}

	public Person getPersonByUsername(String username) throws EntityNotFoundException {
       String queryStr = "select * from person people where people.username = ? ";
       Person result = getSimpleJdbcTemplate().queryForObject(queryStr, getRowMapper(), username);
       if (result == null)
          throw new EntityNotFoundException("Not person with username "
                + username + " found");
       return result;
	}

   protected String getUpdateSql() {
      return "update person set firstName = :firstName, "
            + "lastName = :lastName, username = :username, "
            + "password = :password, roleLevel = :roleLevel "
            + "where id = :id";
   }
}
