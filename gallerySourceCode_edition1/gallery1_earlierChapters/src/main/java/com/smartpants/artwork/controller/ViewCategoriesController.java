package com.smartpants.artwork.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smartpants.artwork.domain.Category;
import com.smartpants.artwork.service.ArtworkFacade;
import com.smartpants.artwork.util.ViewUtil;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 5:53:32 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class ViewCategoriesController implements Controller {
    private ArtworkFacade artworkFacade;
    private String successView;

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
        List<Category> categories = this.getArtworkFacade().getCategories();
        if (categories == null || categories.size() <= 0 ) {
            this.initCategories();
            categories = this.getArtworkFacade().getCategories();
        }
        Map model = new HashMap();
        model.put("categories", categories);
        model.put("posStyle", ViewUtil.buildAlternator(new String[] {"categoryleft", "categoryright" }, 6));
        return new ModelAndView(this.getSuccessView(), "art", model);
    }


    private void initCategories() {
        this.initCategory("Music", "Artwork involving Music");
        this.initCategory("Landscapes", "Landscapes");
        this.initCategory("Figures", "Figures");
        this.initCategory("Compositions and Collages", "Compositions and Collages");
        this.initCategory("Travel Sketches", "Travel Sketches");
        this.initCategory("Drawings", "Drawings");

    }

    private void initCategory(String name, String description) {
        Category cat = new Category();
        cat.setCategoryName(name);
        cat.setCategoryDescription(description);
        this.getArtworkFacade().saveCategory(cat);
    }

    public ArtworkFacade getArtworkFacade() {
        return artworkFacade;
    }

    public void setArtworkFacade(ArtworkFacade artworkFacade) {
        this.artworkFacade = artworkFacade;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
}
