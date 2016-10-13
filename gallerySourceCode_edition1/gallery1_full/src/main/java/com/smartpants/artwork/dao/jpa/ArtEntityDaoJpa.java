package com.smartpants.artwork.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.ArtEntityDao;
import com.smartpants.artwork.domain.ArtEntity;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:08:27 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("artEntityDao")
    @SuppressWarnings("unchecked")
public class ArtEntityDaoJpa extends GenericDaoJpa<ArtEntity> implements ArtEntityDao{

    public ArtEntityDaoJpa(){
        super(ArtEntity.class);
    }

    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException {
        Query query = this.entityManager.createQuery(
                "select art from ArtEntity art where art.title = :title");
        query.setParameter("title", title);
        return (ArtEntity) query.getSingleResult();
    }

    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException {
       Query query = this.entityManager.createQuery(
             "select art from Exhibition exhibits join exhibits.exhibitionArtWork art where exhibits.id = :id");
       query.setParameter("id", exhibitionId);
       return query.getResultList();
    }


    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException {
        Query query = this.entityManager.createQuery(
              "select art from Category cat join cat.artEntities art where cat.id = :catId ");
        query.setParameter("catId", catId);
        return query.getResultList();
    }
}
