package com.smartpants.artwork.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartpants.artwork.domain.ArtData;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 6:38:57 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class DisplayImageController extends AbstractArtController {
    public static final String ARTDATA_ID = "ARTDATA_ID";
    /**
     * Template method. Subclasses must implement this.
     * The contract is the same as for <code>handleRequest</code>.
     *
     * @see #handleRequest
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String artId = request.getParameter(ARTDATA_ID);
        if (artId == null)
            return null;
        Long artId_L = Long.parseLong(artId);
        ArtData artData = this.getArtworkFacade().getArtData(artId_L);
        if (artData != null) {
            FileCopyUtils.copy(artData.getPicture(), response.getOutputStream());
        }
        return null; // abort processing, since we took over the request
    }
}
