package com.smartpants.artwork.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.ArtworkDao;
import com.smartpants.artwork.domain.ArtData;
import com.smartpants.artwork.domain.ArtEntity;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:08:27 AM
 * �Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("artworkDao")
public class ArtworkDaoHibernate extends HibernateDaoSupport implements ArtworkDao{
    private static final String STORAGE_FORMAT = "STORAGE_FORMAT";
    private static final String GALLERY_FORMAT = "GALLERY_FORMAT";
    private static final String THUMBNAIL_FORMAT = "THUMBNAIL_FORMAT";


    @Autowired
    public void setupSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    public ArtEntity getArtEntity(Long id) {
        return (ArtEntity) this.getHibernateTemplate().load(ArtEntity.class, id);
    }

    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException {
        return (ArtEntity) this.getHibernateTemplate().findByNamedParam(
                "select art from ArtEntity art where art.title = :title", "title", title
        );
    }

    public ArtData getArtData(Long id) throws DataAccessException {
        return (ArtData) this.getHibernateTemplate().load(ArtData.class, id);
    }

    // gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException {
        if (imageFormat.equalsIgnoreCase(STORAGE_FORMAT)) {
            if (entity.getStoragePicture() instanceof HibernateProxy)
                this.getHibernateTemplate().initialize(entity.getStoragePicture());
            return entity.getStoragePicture();
        } else if (imageFormat.equalsIgnoreCase(GALLERY_FORMAT)) {
            if (entity.getGalleryPicture() instanceof HibernateProxy)
                this.getHibernateTemplate().initialize(entity.getGalleryPicture());
            return entity.getStoragePicture();
        } else if (imageFormat.equalsIgnoreCase(THUMBNAIL_FORMAT)) {
            if (entity.getThumbnailPicture() instanceof HibernateProxy)
                this.getHibernateTemplate().initialize(entity.getThumbnailPicture());
            return entity.getThumbnailPicture();
        }
        return null;
    }

    public void saveArtEntity(ArtEntity art) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdate(art);
    }

}
