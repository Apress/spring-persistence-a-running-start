package com.smartpants.artwork.domain;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 1, 2006
 * Time: 7:29:10 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 *
 * Serves as a high-res storage of the artwork -- usually the type that is uploaded
 */
@Entity
@DiscriminatorValue("STORAGE")
public class ArtData_Storage extends ArtData implements Serializable {

     public ArtData_Storage(byte[] picture) {
        this.setPicture(picture);
    }

    public ArtData_Storage() {
        
    }

}
