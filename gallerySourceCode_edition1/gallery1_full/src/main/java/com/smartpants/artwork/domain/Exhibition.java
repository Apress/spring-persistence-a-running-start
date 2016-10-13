package com.smartpants.artwork.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:21:12 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@SuppressWarnings("serial")
@Entity
public class Exhibition implements DomainObject{
    private Long id;
    private String exhibitionName;
    private Set<ArtEntity> exhibitionArtWork = new HashSet<ArtEntity>();

    private Integer version;

    public Exhibition() {
    }

    @Id
    @GeneratedValue
    public final Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    @ManyToMany
    public Set<ArtEntity> getExhibitionArtWork() {
        return exhibitionArtWork;
    }

    public void setExhibitionArtWork(Set<ArtEntity> exhibitionArtWork) {
        this.exhibitionArtWork = exhibitionArtWork;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * THis method adds an artEntity to an exhibition
     * It is a unidirectional relationship
     *
     * @param art
     * @return success
     */
    public boolean addArtToExhibition(ArtEntity art) {
        return this.getExhibitionArtWork().add(art);
    }

}
