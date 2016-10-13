package com.smartpants.artwork.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.ExhibitionDao;
import com.smartpants.artwork.domain.ArtEntity;
import com.smartpants.artwork.domain.Exhibition;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:04:33 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("exhibitionDao")  
@SuppressWarnings("unchecked")
public class ExhibitionDaoHibernate extends HibernateDaoSupport implements ExhibitionDao {


    @Autowired
    public void setupSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }


    public List<Exhibition> getExhibitions() throws DataAccessException {
        return this.getHibernateTemplate().find(
                "select exhibitions from Exhibition exhibitions"
        );
    }

    public Exhibition getExhibition(Long exhibitionId) throws DataAccessException {
        return (Exhibition) this.getHibernateTemplate().load(Exhibition.class, exhibitionId);
    }

    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException {
        List<ArtEntity> art = this.getHibernateTemplate().findByNamedParam(
                "select art from Exhibition exhibits join exhibits.exhibitionArtWork art where exhibits.id = :id",
                "id", exhibitionId
        );
        return art;
    }

    public void saveExhibition(Exhibition exhibition) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdate(exhibition);
    }


}
