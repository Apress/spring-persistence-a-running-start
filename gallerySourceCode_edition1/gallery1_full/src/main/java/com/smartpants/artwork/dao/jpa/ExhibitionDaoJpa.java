package com.smartpants.artwork.dao.jpa;

import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.ExhibitionDao;
import com.smartpants.artwork.domain.Exhibition;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:04:33 PM
 * ęCopyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Repository("exhibitionDao")  
public class ExhibitionDaoJpa extends GenericDaoJpa<Exhibition> implements ExhibitionDao {
    public ExhibitionDaoJpa(){
        super(Exhibition.class);
    }
}
