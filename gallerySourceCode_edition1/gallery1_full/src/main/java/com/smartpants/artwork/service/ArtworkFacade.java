package com.smartpants.artwork.service;

import com.smartpants.artwork.domain.*;
import com.smartpants.artwork.exception.AuthenticationException;
import com.smartpants.artwork.exception.EntityNotFoundException;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:12:22 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public interface ArtworkFacade {
    public ArtEntity getArtEntity(Long id) throws DataAccessException;
    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException;

    public ArtData getArtData(Long id) throws DataAccessException;
    // gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException;

    public void saveArtEntity(ArtEntity art) throws DataAccessException;



    public List<Category> getCategories() throws DataAccessException;
    public Category getCategory(Long catId) throws DataAccessException;
    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException;

    public void saveCategory(Category category) throws DataAccessException;


    public List<Exhibition> getExhibitions() throws DataAccessException;
    public Exhibition getExhibition(Long exhibitionId) throws DataAccessException;
    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException;

    public void saveExhibition(Exhibition exhibition) throws DataAccessException;



    public Person getPerson(Long personId) throws DataAccessException;
    public void savePerson(Person person) throws DataAccessException;
    public List<Person> getPeople() throws DataAccessException;
    public Person getPersonByUsername(String username) throws DataAccessException, EntityNotFoundException;
    public Person authenticatePerson(String username, String password) throws DataAccessException, AuthenticationException;



}
