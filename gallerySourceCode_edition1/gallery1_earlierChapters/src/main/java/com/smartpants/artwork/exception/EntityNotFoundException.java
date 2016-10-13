package com.smartpants.artwork.exception;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 3, 2006
 * Time: 5:06:06 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public EntityNotFoundException(String string) {
        super(string);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public EntityNotFoundException(String string, Throwable throwable) {
        super(string, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public EntityNotFoundException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
