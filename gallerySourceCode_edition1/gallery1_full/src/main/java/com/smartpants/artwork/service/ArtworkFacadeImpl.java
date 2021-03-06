package com.smartpants.artwork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartpants.artwork.dao.ArtDataDao;
import com.smartpants.artwork.dao.ArtEntityDao;
import com.smartpants.artwork.dao.CategoryDao;
import com.smartpants.artwork.dao.ExhibitionDao;
import com.smartpants.artwork.dao.PersonDao;
import com.smartpants.artwork.domain.ArtData;
import com.smartpants.artwork.domain.ArtEntity;
import com.smartpants.artwork.domain.Category;
import com.smartpants.artwork.domain.Exhibition;
import com.smartpants.artwork.domain.Person;
import com.smartpants.artwork.exception.AuthenticationException;
import com.smartpants.artwork.exception.EntityNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:09:59 AM
 * ęCopyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Service(value = "artworkFacade")
@Transactional  
public class ArtworkFacadeImpl implements ArtworkFacade {
    private ArtEntityDao artEntityDao;
    private ArtDataDao artDataDao;
    private CategoryDao categoryDao;
    private ExhibitionDao exhibitionDao;
    private PersonDao personDao;    

    //*************Artwork DAO Methods *************/
    @Transactional(readOnly = true)
    public ArtEntity getArtEntity(Long id) throws DataAccessException {
        return artEntityDao.get(id);
    }

    @Transactional(readOnly = true)
    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException {
        return artEntityDao.getArtEntityByTitle(title);
    }

    @Transactional(readOnly = true)
    public ArtData getArtData(Long id) throws DataAccessException {
        return artDataDao.get(id);
    }

    @Transactional(readOnly = true)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException {
        return artDataDao.getArtDataFromEntity(entity, imageFormat);
    }

    @Transactional(readOnly = false)
    public void saveArtEntity(ArtEntity art) throws DataAccessException {
        artEntityDao.save(art);
    }

    @Transactional(readOnly = true)
    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException {
        return artEntityDao.getArtInExhibition(exhibitionId);
    }

    //*************Category DAO Methods *************/

    @Transactional(readOnly = true)
    public List<Category> getCategories() throws DataAccessException {
        return categoryDao.getAll();
    }
    @Transactional(readOnly = true)
    public Category getCategory(Long catId) throws DataAccessException {
        return categoryDao.get(catId);
    }
    @Transactional(readOnly = true)
    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException {
        return artEntityDao.getArtworkInCategory(catId);
    }
    @Transactional(readOnly = false)
    public void saveCategory(Category category) throws DataAccessException {
        categoryDao.save(category);
    }

    //*************Exhibition DAO Methods *************/
    @Transactional(readOnly = true)
    public List<Exhibition> getExhibitions() throws DataAccessException {
        return exhibitionDao.getAll();
    }
    @Transactional(readOnly = true)
    public Exhibition getExhibition(Long exhibitionId) throws DataAccessException {
        return exhibitionDao.get(exhibitionId);
    }
    @Transactional(readOnly = false)
    public void saveExhibition(Exhibition exhibition) throws DataAccessException {
        exhibitionDao.save(exhibition);
    }


    //*************Person DAO Methods *************/

    @Transactional(readOnly = true)
    public Person getPerson(Long personId) throws DataAccessException {
        return personDao.get(personId);
    }

    @Transactional(readOnly = false)
    public void savePerson(Person person) throws DataAccessException {
        personDao.save(person);
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
        return personDao.getAll();
    }

    public ArtEntityDao getArtEntityDao() {
       return artEntityDao;
    }

    @Autowired
    public void setArtEntityDao(ArtEntityDao artEntityDao) {
       this.artEntityDao = artEntityDao;
    }

    public ArtDataDao getArtDataDao()
    {
        return artDataDao;
    }

    @Autowired
    public void setArtDataDao(ArtDataDao artDataDao){
        this.artDataDao = artDataDao;
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
