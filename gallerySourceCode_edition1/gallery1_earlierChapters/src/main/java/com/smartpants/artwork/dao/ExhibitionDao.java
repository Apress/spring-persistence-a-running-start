package com.smartpants.artwork.dao;

import com.smartpants.artwork.domain.ArtEntity;
import com.smartpants.artwork.domain.Exhibition;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:41:36 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public interface ExhibitionDao {

    public List<Exhibition> getExhibitions() throws DataAccessException;
    public Exhibition getExhibition(Long exhibitionId) throws DataAccessException;
    public List<ArtEntity> getArtInExhibition(Long exhibitionId) throws DataAccessException;

    public void saveExhibition(Exhibition exhibition) throws DataAccessException;

    
}
