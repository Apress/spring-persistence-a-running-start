package com.smartpants.artwork.dao.jpa;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.ArtDataDao;
import com.smartpants.artwork.domain.ArtData;
import com.smartpants.artwork.domain.ArtEntity;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:08:27 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("artDataDao")
public class ArtDataDaoJpa extends GenericDaoJpa<ArtData> implements ArtDataDao{

   public ArtDataDaoJpa(){
      super(ArtData.class);
   }

    private static final String STORAGE_FORMAT = "STORAGE_FORMAT";
    private static final String GALLERY_FORMAT = "GALLERY_FORMAT";
    private static final String THUMBNAIL_FORMAT = "THUMBNAIL_FORMAT";

    // gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException {
        if (imageFormat.equalsIgnoreCase(STORAGE_FORMAT)) {
//            if (entity.getStoragePicture() instanceof HibernateProxy)
//                this.getHibernateTemplate().initialize(entity.getStoragePicture());
            return entity.getStoragePicture();
        } else if (imageFormat.equalsIgnoreCase(GALLERY_FORMAT)) {
//            if (entity.getGalleryPicture() instanceof HibernateProxy)
//                this.getHibernateTemplate().initialize(entity.getGalleryPicture());
            return entity.getStoragePicture();
        } else if (imageFormat.equalsIgnoreCase(THUMBNAIL_FORMAT)) {
//            if (entity.getThumbnailPicture() instanceof HibernateProxy)
//                this.getHibernateTemplate().initialize(entity.getThumbnailPicture());
            return entity.getThumbnailPicture();
        }
        return null;
    }
}
