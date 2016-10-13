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
public interface ArtworkDao {

    public ArtEntity getArtEntity(Long id) throws DataAccessException;
    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException;

    public ArtData getArtData(Long id) throws DataAccessException;
    // gets (loading proxy) appropriate image format from artEntity (such as thumbnail, etc.)
    public ArtData getArtDataFromEntity(ArtEntity entity, String imageFormat) throws DataAccessException;

    public void saveArtEntity(ArtEntity art) throws DataAccessException;




}
