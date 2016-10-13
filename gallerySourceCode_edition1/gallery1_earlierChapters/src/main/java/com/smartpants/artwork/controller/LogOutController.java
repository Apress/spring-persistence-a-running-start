package com.smartpants.artwork.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 3, 2006
 * Time: 5:42:34 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
@Controller()
public class LogOutController extends AbstractArtController {

    /**
     * Template method. Subclasses must implement this.
     * The contract is the same as for <code>handleRequest</code>.
     *
     * @see #handleRequest
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        return new ModelAndView(new RedirectView(this.getSuccessView()));
    }

}
