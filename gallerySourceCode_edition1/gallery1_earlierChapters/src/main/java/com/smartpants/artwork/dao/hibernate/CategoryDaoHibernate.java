package com.smartpants.artwork.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.CategoryDao;
import com.smartpants.artwork.domain.ArtEntity;
import com.smartpants.artwork.domain.Category;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:04:05 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("categoryDao")
@SuppressWarnings("unchecked")
public class CategoryDaoHibernate extends HibernateDaoSupport implements CategoryDao {

    @Autowired
    public void setOverrideSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public List<Category> getCategories() throws DataAccessException {
        return this.getHibernateTemplate().find(
                "select categories from Category categories"
        );
    }

    public Category getCategory(Long catId) throws DataAccessException {
        return (Category) this.getHibernateTemplate().load(Category.class, catId);
    }

    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException {
        return this.getHibernateTemplate().findByNamedParam(
                "select art from Category cat join cat.artEntities art where cat.id = :catId ", "catId", catId
        );
    }

    public void saveCategory(Category category) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdate(category);
    }


}
