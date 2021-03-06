package com.smartpants.artwork.domain;

import org.hibernate.annotations.*;
import org.junit.Ignore;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:19:25 PM
 * �Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 *
 * References an uploaded piece of artwork
 * Stores multiple references to the actual digital content
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ArtEntity implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private Date uploadedDate;
    private String displayDate;
    private Integer width;
    private Integer height;
    private String media;
    private String description;
    private String caption;
    private ArtData_Gallery galleryPicture;
    private ArtData_Storage storagePicture;
    private ArtData_Thumbnail thumbnailPicture;
    private Boolean isGeneralViewable;
    private Boolean isPrivilegeViewable; // can be seen by logged-in non-administrators (spcial visitors)
    private Set<Category> categories = new HashSet();
    private Set<Comment> comments = new HashSet();

    private String convertedTitle; //transient

    private Integer version;


    public ArtEntity() {

    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    public ArtData_Gallery getGalleryPicture() {
        return galleryPicture;
    }

    public void setGalleryPicture(ArtData_Gallery galleryPicture) {
        this.galleryPicture = galleryPicture;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    public ArtData_Storage getStoragePicture() {
        return storagePicture;
    }

    public void setStoragePicture(ArtData_Storage storagePicture) {
        this.storagePicture = storagePicture;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    public ArtData_Thumbnail getThumbnailPicture() {
        return thumbnailPicture;
    }

    public void setThumbnailPicture(ArtData_Thumbnail thumbnailPicture) {
        this.thumbnailPicture = thumbnailPicture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

//    @Temporal(value=TemporalType.DATE)
    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

//    @Temporal(value=TemporalType.DATE)
    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Boolean getGeneralViewable() {
        return isGeneralViewable;
    }

    public void setGeneralViewable(Boolean generalViewable) {
        isGeneralViewable = generalViewable;
    }

    public Boolean getPrivilegeViewable() {
        return isPrivilegeViewable;
    }

    public void setPrivilegeViewable(Boolean privilegeViewable) {
        isPrivilegeViewable = privilegeViewable;
    }

    @ManyToMany(mappedBy = "artEntities")
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }



    @OneToMany(cascade = {javax.persistence.CascadeType.ALL})
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean addCommentToArt(Comment comment) {
        comment.setCommentedArt(this);
        return this.getComments().add(comment);
    }


    @Transient
    public String getSafeTitle() {
        if (this.convertedTitle == null) {
            this.convertedTitle = this.getTitle().replaceAll("\\W", "_");
        }
        return this.convertedTitle;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ArtEntity artEntity = (ArtEntity) o;

        if (description != null ? !description.equals(artEntity.description) : artEntity.description != null) return false;
        if (displayDate != null ? !displayDate.equals(artEntity.displayDate) : artEntity.displayDate != null) return false;
        if (height != null ? !height.equals(artEntity.height) : artEntity.height != null) return false;
        if (subTitle != null ? !subTitle.equals(artEntity.subTitle) : artEntity.subTitle != null) return false;
        if (title != null ? !title.equals(artEntity.title) : artEntity.title != null) return false;
        if (uploadedDate != null ? !uploadedDate.equals(artEntity.uploadedDate) : artEntity.uploadedDate != null)
            return false;
        if (width != null ? !width.equals(artEntity.width) : artEntity.width != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (title != null ? title.hashCode() : 0);
        result = 29 * result + (subTitle != null ? subTitle.hashCode() : 0);
        result = 29 * result + (uploadedDate != null ? uploadedDate.hashCode() : 0);
        result = 29 * result + (displayDate != null ? displayDate.hashCode() : 0);
        result = 29 * result + (width != null ? width.hashCode() : 0);
        result = 29 * result + (height != null ? height.hashCode() : 0);
        result = 29 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
