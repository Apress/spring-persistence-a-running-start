package com.smartpants.artwork.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:28:18 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 *
 * Serves as the artwork th umbnail
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("THUMBNAIL")
public class ArtData_Thumbnail extends ArtData {

    public ArtData_Thumbnail(byte[] picture) {
        this.setPicture(picture);
    }

    public ArtData_Thumbnail() {
    }
}
