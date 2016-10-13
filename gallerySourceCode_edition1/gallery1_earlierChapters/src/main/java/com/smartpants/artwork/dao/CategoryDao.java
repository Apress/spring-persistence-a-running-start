package com.smartpants.artwork.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.smartpants.artwork.domain.ArtEntity;
import com.smartpants.artwork.domain.Category;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:41:24 AM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public interface CategoryDao {

    public List<Category> getCategories() throws DataAccessException;
    public Category getCategory(Long catId) throws DataAccessException;
    public List<ArtEntity> getArtworkInCategory(Long catId) throws DataAccessException;

    public void saveCategory(Category category) throws DataAccessException;
}
