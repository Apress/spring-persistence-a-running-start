package com.smartpants.artwork.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.smartpants.artwork.domain.ArtEntity;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:08:18 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public interface ArtEntityDao extends GenericDao<ArtEntity>{
    public ArtEntity getArtEntityByTitle(String title) throws DataAccessException;
    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException;
    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException;
}
