package com.smartpants.artwork.dao;

import org.springframework.dao.DataAccessException;

import com.smartpants.artwork.domain.ArtData;
import com.smartpants.artwork.domain.ArtEntity;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:08:18 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public interface ArtDataDao extends GenericDao<ArtData>{
    // gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException;
}
