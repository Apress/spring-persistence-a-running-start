package com.smartpants.artwork.dao.hibernate;

import java.util.List;

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
public class ArtEntityDaoHibernate extends GenericDaoHibernate<ArtEntity> implements ArtEntityDao{

   public ArtEntityDaoHibernate(){
      super(ArtEntity.class);
   }

    @SuppressWarnings("unchecked")
    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException {
        final List<ArtEntity> results = this.getHibernateTemplate().findByNamedParam(
                "select art from ArtEntity art where art.title = :title", "title", title
        );
        return results.isEmpty() ? null : results.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException {
        return this.getHibernateTemplate().findByNamedParam(
                "select art from Exhibition exhibits join exhibits.exhibitionArtWork art where exhibits.id = :id",
                "id", exhibitionId
        );
    }
    
    @SuppressWarnings("unchecked")
     public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException {
        return this.getHibernateTemplate().findByNamedParam(
                "select art from Category cat join cat.artEntities art where cat.id = :catId ", "catId", catId
        );
    }
}
