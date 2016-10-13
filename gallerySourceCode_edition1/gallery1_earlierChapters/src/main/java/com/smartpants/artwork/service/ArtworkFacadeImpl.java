package com.smartpants.artwork.service;

import com.smartpants.artwork.dao.ArtworkDao;
import com.smartpants.artwork.dao.CategoryDao;
import com.smartpants.artwork.dao.ExhibitionDao;
import com.smartpants.artwork.dao.PersonDao;
import com.smartpants.artwork.domain.*;
import com.smartpants.artwork.exception.AuthenticationException;
import com.smartpants.artwork.exception.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:09:59 AM
 * �Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Service(value = "artworkFacade")
@Transactional  
public class ArtworkFacadeImpl implements ArtworkFacade {
    private ArtworkDao artworkDao;
    private CategoryDao categoryDao;
    private ExhibitionDao exhibitionDao;
    private PersonDao personDao;

    

    //*************Artwork DAO Methods *************/
    @Transactional(readOnly = true)
    public ArtEntity getArtEntity(Long id) throws DataAccessException {
        return artworkDao.getArtEntity(id);
    }
    @Transactional(readOnly = true)
    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException {
        return artworkDao.getArtEntityByTitle(title);
    }
    @Transactional(readOnly = true)
    public ArtData getArtData(Long id) throws DataAccessException {
        return artworkDao.getArtData(id);
    }
    @Transactional(readOnly = true)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException {
        return artworkDao.getArtDataFromEntity(entity, imageFormat);
    }

    @Transactional(readOnly = false)
    public void saveArtEntity(ArtEntity art) throws DataAccessException {
        artworkDao.saveArtEntity(art);
    }


    //*************Category DAO Methods *************/

    @Transactional(readOnly = true)
    public List<Category> getCategories() throws DataAccessException {
        return categoryDao.getCategories();
    }
    @Transactional(readOnly = true)
    public Category getCategory(Long catId) throws DataAccessException {
        return categoryDao.getCategory(catId);
    }
    @Transactional(readOnly = true)
    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException {
        return categoryDao.getArtworkInCategory(catId);
    }
    @Transactional(readOnly = false)
    public void saveCategory(Category category) throws DataAccessException {
        categoryDao.saveCategory(category);
    }

    //*************Exhibition DAO Methods *************/
    @Transactional(readOnly = true, propagation = Propagation.NESTED, timeout = 10000, rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public List<Exhibition> getExhibitions() throws DataAccessException {
        return exhibitionDao.getExhibitions();
    }
    @Transactional(readOnly = true)
    public Exhibition getExhibition(Long exhibitionId) throws DataAccessException {
        return exhibitionDao.getExhibition(exhibitionId);
    }
    @Transactional(readOnly = true)
    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException {
        return exhibitionDao.getArtInExhibition(exhibitionId);
    }
    @Transactional(readOnly = false)
    public void saveExhibition(Exhibition exhibition) throws DataAccessException {
        exhibitionDao.saveExhibition(exhibition);
    }


    //*************Person DAO Methods *************/

    @Transactional(readOnly = true)
    public Person getPerson(Long personId) throws DataAccessException {
        return personDao.getPerson(personId);
    }

    @Transactional(readOnly = false)
    public void savePerson(Person person) throws DataAccessException {
        personDao.savePerson(person);
    }
    @Transactional(readOnly = true)
    public Person authenticatePerson(String username, String password) throws DataAccessException, AuthenticationException {
        return personDao.authenticatePerson(username, password);
    }
    @Transactional(readOnly = true)
    public Person getPersonByUsername(String username) throws DataAccessException, EntityNotFoundException {
        return personDao.getPersonByUsername(username);
    }
    @Transactional(readOnly = true)
    public List<Person> getPeople() throws DataAccessException {
        return personDao.getPeople();
    }


    public ArtworkDao getArtworkDao() {
        return artworkDao;
    }

    @Autowired
    public void setArtworkDao(ArtworkDao artworkDao) {
        this.artworkDao = artworkDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public ExhibitionDao getExhibitionDao() {
        return exhibitionDao;
    }
    @Autowired
    public void setExhibitionDao(ExhibitionDao exhibitionDao) {
        this.exhibitionDao = exhibitionDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }
    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}
