package com.smartpants.artwork.controller;

import org.springframework.web.servlet.mvc.Controller;
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
 * Time: 6:08:23 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class SelectCategoryController extends AbstractArtController {
    private static final String CATEGORY_ID = "CATEGORY_ID";


    /**
     * Template method. Subclasses must implement this.
     * The contract is the same as for <code>handleRequest</code>.
     *
     * @see #handleRequest
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String catId = request.getParameter(CATEGORY_ID);
        if (catId == null || catId.length() <= 0 )
            return new ModelAndView(this.getFormView());
        Long catId_L = Long.parseLong(catId);
        Category cat = this.getArtworkFacade().getCategory(catId_L);
        List<ArtEntity> artwork = this.getArtworkFacade().getArtworkInCategory(catId_L);
        ArtEntity selectedArt = null;
        if (artwork.size() > 0) {
            selectedArt = artwork.get(0);
        } else {
            // make fake selected art
            selectedArt = new ArtEntity();
            selectedArt.setId(0L);
            selectedArt.setTitle("No Artwork Available");
            selectedArt.setDescription("No Description Available");
        }


        Map model = this.getViewArtModelAndView(artwork, selectedArt, cat);

        //make sure referenced views have a hidden field with the category and other required reference data
        return new ModelAndView(this.getSuccessView(), "art", model);
    }





}
