package com.smartpants.artwork.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:28:51 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 *
 * Serves as the image designed for web-site viewing in gallery window
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("GALLERY")
public class ArtData_Gallery extends ArtData {

    public ArtData_Gallery(byte[] picture) {
        this.setPicture(picture);
    }

    public ArtData_Gallery() {
        
    }

}
