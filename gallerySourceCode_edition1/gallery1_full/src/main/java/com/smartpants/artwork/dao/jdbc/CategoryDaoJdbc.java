package com.smartpants.artwork.dao.jdbc;

import org.springframework.stereotype.Repository;

import com.smartpants.artwork.dao.CategoryDao;
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
public class CategoryDaoJdbc extends GenericDaoJdbc<Category> implements CategoryDao {

    public CategoryDaoJdbc() {
        super(Category.class, "category");
    }

    @Override
    protected String getUpdateSql() {
        return "update category set categoryName = :categoryName, categoryDescription = :categoryDescription where id = :id";
    }

}
