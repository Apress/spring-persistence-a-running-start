package com.smartpants.artwork.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.springframework.web.bind.RequestUtils;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartpants.artwork.domain.Person;
import com.smartpants.artwork.exception.AuthenticationException;
import com.smartpants.artwork.service.ArtworkFacade;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 3:30:35 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class LoginController implements Controller {
    private ArtworkFacade artworkFacade;
    public static final String USERNAME_PARAM = "username";
    public static final String PASSWORD_PARAM = "password";
    public static final String LOGIN_FAILURE = "LOGIN_FAILURE";
    public static final String LOGIN_VIEW = "admin_login";
    public static final String LOGGED_IN_USER = "LOGGED_IN_USER";
    public static final String ADMINMENU_VIEW = "admin_menu";

    private String successView;
    private String formView;

    /**
     * Process the request and return a ModelAndView object which the DispatcherServlet
     * will render. A <code>null</code> return value is not an error: It indicates that
     * this object completed request processing itself, thus there is no ModelAndView
     * to render.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @return a ModelAndView to render, or <code>null</code> if handled directly
     * @throws Exception in case of errors
     */
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter(USERNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        if (username == null || password == null) {
            Map errorModel = new HashMap();
            errorModel.put(LOGIN_FAILURE, "A valid username and password must be supplied");
            return new ModelAndView(this.getFormView(), errorModel);
        }
        Person person = null;
        try {
            person = this.getArtworkFacade().authenticatePerson(username, password);
        } catch (DataAccessException e) {
            person = null;
        } catch (AuthenticationException e) {
            person = null;
        }

        if (person == null) {
            Map errorModel = new HashMap();
            errorModel.put(LOGIN_FAILURE, "You could not be authenticated. Please try again.");
            return new ModelAndView(this.getFormView(), errorModel);
        }

        //successful, add person to session
        WebUtils.setSessionAttribute(request, LOGGED_IN_USER, person);
        return new ModelAndView(this.getSuccessView());

    }


    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public String getFormView() {
        return formView;
    }

    public void setFormView(String formView) {
        this.formView = formView;
    }

    public ArtworkFacade getArtworkFacade() {
        return artworkFacade;
    }

    public void setArtworkFacade(ArtworkFacade artworkFacade) {
        this.artworkFacade = artworkFacade;
    }

}
