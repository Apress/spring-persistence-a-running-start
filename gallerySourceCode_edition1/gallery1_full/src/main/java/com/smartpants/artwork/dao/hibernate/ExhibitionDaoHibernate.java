package com.smartpants.artwork.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.ExhibitionDao;
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
public class ExhibitionDaoHibernate extends GenericDaoHibernate<Exhibition> implements ExhibitionDao {
    public ExhibitionDaoHibernate(){
        super(Exhibition.class);
    }
}
