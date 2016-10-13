package com.smartpants.artwork.dao.hibernate;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.PersonDao;
import com.smartpants.artwork.domain.Person;
import com.smartpants.artwork.exception.AuthenticationException;
import com.smartpants.artwork.exception.EntityNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:04:55 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("personDao")
@SuppressWarnings("unchecked")
public class PersonDaoHibernate extends GenericDaoHibernate<Person> implements PersonDao {

   public PersonDaoHibernate(){
      super(Person.class);
   }

	public Person authenticatePerson(String username, String password) throws DataAccessException, AuthenticationException {
        List<Person> validUsers = this.getHibernateTemplate().findByNamedParam(
                "select people from Person people where people.username = :username and people.password = :password",
                new String[] {"username", "password"}, new String[] {username, password }
        );
        if (validUsers == null || validUsers.size() <= 0)
            throw new AuthenticationException("No users found");
        return validUsers.get(0);
    }

    public Person getPersonByUsername(String username) throws DataAccessException, EntityNotFoundException {
        List<Person> people = this.getHibernateTemplate().findByNamedParam(
                "select people from Person people where people.username = :username", "username", username
        );
        if (people == null || people.size() <= 0)
            throw new EntityNotFoundException("Not person with username " + username + " found");
        return people.get(0);
    }

}
