package com.smartpants.artwork.dao.jdbc;

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
// TODO: deal with exhibit.exhibitionArtWork
public class ExhibitionDaoJdbc extends GenericDaoJdbc<Exhibition> implements ExhibitionDao {
    public ExhibitionDaoJdbc(){
        super(Exhibition.class, "exhibition");
    }

    @Override
    protected String getUpdateSql() {
        return "update exhibition set exhibitionName = :exhibitionName where id = :id";
    }

}
