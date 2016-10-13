package com.smartpants.artwork.dao;

import java.util.List;

import com.smartpants.artwork.domain.Person;
import com.smartpants.artwork.exception.AuthenticationException;
import com.smartpants.artwork.exception.EntityNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:41:54 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public interface PersonDao {

    public Person getPerson(Long personId) ;
    public void savePerson(Person person) ;
    public List<Person> getPeople() ;
    public Person getPersonByUsername(String username) throws EntityNotFoundException ;

    public Person authenticatePerson(String username, String password) throws AuthenticationException;

}
