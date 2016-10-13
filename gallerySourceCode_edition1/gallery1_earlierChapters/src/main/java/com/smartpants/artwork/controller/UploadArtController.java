package com.smartpants.artwork.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.validation.Errors;
import org.springframework.validation.BindException;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.smartpants.artwork.converter.ArtDataMultipartFileEditor;
import com.smartpants.artwork.converter.ImageHandler;
import com.smartpants.artwork.domain.*;
import com.smartpants.artwork.service.ArtworkFacade;

/**
 * Created by IntelliJ IDEA.
 * Author: Paul T. Fisher
 * User: paul
 * Date: Feb 2, 2006
 * Time: 4:16:21 PM
 * ©Copyright 2005, SmartPants Media, Inc. All Rights Reserved.
 */
public class UploadArtController extends SimpleFormController {
    private ArtworkFacade artworkFacade;

    /**
     * Simplest <code>onSubmit</code> version. Called by the default implementation
     * of the <code>onSubmit</code> version with command and BindException parameters.
     * <p>This implementation calls <code>doSubmitAction</code> and returns null
     * as ModelAndView, making the calling onSubmit method perform its default
     * rendering of the success view.
     * <p>Subclasses can override this to provide custom submission handling
     * that just depends on the command object. It's preferable to use either
     * <code>onSubmit(command, errors)</code> or <code>doSubmitAction(command)</code>,
     * though: Use the former when you want to build your own ModelAndView; use the
     * latter when you want to perform an action and forward to the successView.
     *
     * @param command form object with request parameters bound onto it
     * @return the prepared model and view, or <code>null</code> for default (i.e. successView)
     * @throws Exception in case of errors
     * @see #onSubmit(Object, org.springframework.validation.BindException)
     * @see #doSubmitAction
     * @see #setSuccessView
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
       Set<Category> selectedCategories = this.getSelectedCategories(request);
       ArtEntity artEntity = (ArtEntity) command;

       ImageHandler imageHandler = new ImageHandler();

       byte[] thumbnailData = imageHandler.convertToThumb(artEntity.getStoragePicture().getPicture());
       byte[] galleryData = imageHandler.convertToGallery(artEntity.getStoragePicture().getPicture());
       artEntity.setThumbnailPicture(new ArtData_Thumbnail(thumbnailData));
       artEntity.setGalleryPicture(new ArtData_Gallery(galleryData));
       this.getArtworkFacade().saveArtEntity(artEntity); 
        for (Category category : selectedCategories) {
            category.addArtToCategory(artEntity);
            this.getArtworkFacade().saveCategory(category);
        }
       this.getArtworkFacade().saveArtEntity(artEntity);
       return new ModelAndView(this.getSuccessView());
    }


    private Set<Category> getSelectedCategories(HttpServletRequest request) {
        Set<Category> selectedCats = new HashSet<Category>();
        List<Category> availCats = this.getArtworkFacade().getCategories();
        for (Category curCat : availCats) {
            if (request.getParameterMap().containsKey(curCat.getCategoryName())) {
                // category has been selected
                selectedCats.add(curCat);
            }
        }
        return selectedCats;
    }
    /**
     * Create a reference data map for the given request and command,
     * consisting of bean name/bean instance pairs as expected by ModelAndView.
     * <p>Default implementation delegates to referenceData(request).
     * Subclasses can override this to set reference data used in the view.
     *
     * @param request current HTTP request
     * @param command form object with request parameters bound onto it
     * @param errors  validation errors holder
     * @return a Map with reference data entries, or <code>null</code> if none
     * @throws Exception in case of invalid state or arguments
     * @see org.springframework.web.servlet.ModelAndView
     */
    @Override
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
        // get Categories
        List<Category> categories = this.getArtworkFacade().getCategories();
        // get Exhibits
        List<Exhibition> exhibits = this.getArtworkFacade().getExhibitions();
        Map refData = new HashMap();
        refData.put("categories", categories);
        refData.put("exhibits", exhibits);
        return refData;
    }

    /**
     * Initialize the given binder instance, for example with custom editors.
     * Called by <code>createBinder</code>.
     * <p>This method allows you to register custom editors for certain fields of your
     * command class. For instance, you will be able to transform Date objects into a
     * String pattern and back, in order to allow your JavaBeans to have Date properties
     * and still be able to set and display them in an HTML interface.
     * <p>Default implementation is empty.
     *
     * @param request current HTTP request
     * @param binder  new binder instance
     * @throws Exception in case of invalid state or arguments
     * @see #createBinder
     * @see org.springframework.validation.DataBinder#registerCustomEditor
     * @see org.springframework.beans.propertyeditors.CustomDateEditor
     */
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);    //To change body of overridden methods use File | Settings | File Templates.
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        binder.registerCustomEditor(ArtData_Storage.class, new ArtDataMultipartFileEditor());
        DateFormat dateStyle = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateStyle, true));
    }

    /**
     * Retrieve a backing object for the current form from the given request.
     * <p>The properties of the form object will correspond to the form field values
     * in your form view. This object will be exposed in the model under the specified
     * command name, to be accessed under that name in the view: for example, with
     * a "spring:bind" tag. The default command name is "command".
     * <p>Note that you need to activate session form mode to reuse the form-backing
     * object across the entire form workflow. Else, a new instance of the command
     * class will be created for each submission attempt, just using this backing
     * object as template for the initial form.
     * <p>Default implementation calls <code>BaseCommandController.createCommand</code>,
     * creating a new empty instance of the command class.
     * Subclasses can override this to provide a preinitialized backing object.
     *
     * @param request current HTTP request
     * @return the backing object
     * @throws Exception in case of invalid state or arguments
     * @see #setCommandName
     * @see #setCommandClass
     * @see #createCommand
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return super.formBackingObject(request);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ArtworkFacade getArtworkFacade() {
        return artworkFacade;
    }

    public void setArtworkFacade(ArtworkFacade artworkFacade) {
        this.artworkFacade = artworkFacade;
    }
}
