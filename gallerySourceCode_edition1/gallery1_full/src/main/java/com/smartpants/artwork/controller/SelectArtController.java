package com.smartpants.artwork.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartpants.artwork.domain.ArtEntity;
import com.smartpants.artwork.domain.Category;
import com.smartpants.artwork.util.ViewUtil;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 6:31:25 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class SelectArtController extends AbstractArtController {
    private static final String CATEGORY_ID = "CATEGORY_ID";
    private static final String SELECTEDART_ID = "SELECTEDART_ID";
    /**
     * Template method. Subclasses must implement this.
     * The contract is the same as for <code>handleRequest</code>.
     *
     * @see #handleRequest
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String catId = request.getParameter(CATEGORY_ID);
        String artId = request.getParameter(SELECTEDART_ID);
        if (artId == null || artId.length() <= 0 ||  catId == null || catId.length() <= 0 )
            return new ModelAndView(this.getFormView());
        Long catId_L = Long.parseLong(catId);
        Long artId_L = Long.parseLong(artId);
        Category cat = this.getArtworkFacade().getCategory(catId_L);
        List<ArtEntity> artwork = this.getArtworkFacade().getArtworkInCategory(catId_L);
        ArtEntity selectedArt = this.getArtworkFacade().getArtEntity(artId_L);

        Map model = this.getViewArtModelAndView(artwork, selectedArt, cat);
        
        //make sure referenced views have a hidden field with the category and other required reference data
        return new ModelAndView(this.getSuccessView(), "art", model);
    }
}
