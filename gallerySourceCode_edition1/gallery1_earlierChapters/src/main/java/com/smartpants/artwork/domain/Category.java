package com.smartpants.artwork.domain;

import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:18:57 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Entity
public class Category implements Serializable {
    private Long id;
    private String categoryName;
    private String categoryDescription;
    private Set<ArtEntity> artEntities = new HashSet();


    public Category() {

    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @ManyToMany
    public Set<ArtEntity> getArtEntities() {
        return artEntities;
    }

    public void setArtEntities(Set<ArtEntity> artEntities) {
        this.artEntities = artEntities;
    }

    /**
     * This method controls the adding of the art_to_categories many-to-many association
     * @param art
     * @return success
     */
    public boolean addArtToCategory(ArtEntity art) {
        art.getCategories().add(this);
        return this.getArtEntities().add(art);
    }
}
