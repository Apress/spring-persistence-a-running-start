package com.smartpants.artwork.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:26:14 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 *
 * Represents the actual data for a piece of art
 */

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("GENERIC")
public class ArtData implements Serializable {
    private Long id;
    private byte[] picture;
    private Integer version;

    public ArtData() {

    }

    public ArtData(byte[] picture) {
        this.picture = picture;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
