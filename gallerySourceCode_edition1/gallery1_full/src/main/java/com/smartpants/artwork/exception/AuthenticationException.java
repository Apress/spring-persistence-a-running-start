package com.smartpants.artwork.exception;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 1:00:37 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public AuthenticationException(String string) {
        super(string);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public AuthenticationException(String string, Throwable throwable) {
        super(string, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
