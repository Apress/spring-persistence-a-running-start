package com.smartpants.artwork.dao.jdbc;

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
public class ArtEntityDaoJdbc extends GenericDaoJdbc<ArtEntity> implements ArtEntityDao{

   public ArtEntityDaoJdbc(){
      super(ArtEntity.class, "ArtEntity");
   }


   public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException {
      return queryByType(
            "select ae.* " + 
            "from Category_ArtEntity cae inner join ArtEntity ae on cae.ArtEntityId = ae.id " +
            "where cae.CategoryId = ? ", catId);
  }

   public ArtEntity getArtEntityByTitle(String title) throws DataAccessException {
        return gueryForInstance("select art.* from ArtEntity art where art.title = ?", title);
    }


   protected String getUpdateSql()
   {
      return null;
   }


   @Override
   public List<ArtEntity> getArtInExhibition(Long exhibitionId)
         throws DataAccessException
   {
      return null;
   }
}
