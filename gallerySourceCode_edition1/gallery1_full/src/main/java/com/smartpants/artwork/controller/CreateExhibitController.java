package com.smartpants.artwork.controller;

import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.smartpants.artwork.domain.Exhibition;
import com.smartpants.artwork.service.ArtworkFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: paul
 * Date: Mar 9, 2008
 * Time: 9:27:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateExhibitController extends AbstractController {
    private ArtworkFacade artworkFacade;
    private String formView;
    private String successView;
    public static String EXHIBIT_NAME_PARAM = "exhibitionName";
    protected static Log log = LogFactory.getLog(CreateExhibitController.class);



    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String exhibit = httpServletRequest.getParameter(EXHIBIT_NAME_PARAM);


//        if (httpServletRequest.getParameter("cancel") != null) {
//            return new ModelAndView(this.getFormView());
//        }
        if (exhibit != null && exhibit.length() > 0) {
            log.debug("Adding exhibit: " + exhibit);
            List<Exhibition> exhibitions = this.getArtworkFacade().getExhibitions();
            if (exhibitions != null) {
                for (Exhibition curExhibition : exhibitions) {
                    if (curExhibition.getExhibitionName().equalsIgnoreCase(exhibit)) {
                        log.error("can't create exhibit, the exhibit already exists!");
                        Map errorModel = new HashMap();
                        errorModel.put("error", "Exhibit with the name " + exhibit + " already exists!");
                        return new ModelAndView(this.getFormView(), errorModel);
                    }
                }
            }
            Exhibition exhibition = new Exhibition();
            exhibition.setExhibitionName(exhibit);
            this.getArtworkFacade().saveExhibition(exhibition);
            log.debug("Exhibit saved");
            Map model = new HashMap();
            model.put("exhibit", exhibition);
            return new ModelAndView(this.getSuccessView(), model);
        }

        return new ModelAndView(this.getFormView());
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
