
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.SearchTemplateService;
import domain.Chorbi;
import domain.SearchTemplate;

@Controller
@RequestMapping("/searchTemplate/chorbi")
public class SearchTemplateController extends AbstractController {

	// Constructors -----------------------------------------------------------
	public SearchTemplateController() {
		super();
	}


	// Services ---------------------------------------------------------------
	@Autowired
	private SearchTemplateService	searchTemplateService;

	@Autowired
	private ChorbiService			chorbiService;


	// Display ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Chorbi chorbi;
		SearchTemplate searchTemplate;

		chorbi = this.chorbiService.findByPrincipal();

		searchTemplate = chorbi.getSearchTemplate();

		result = new ModelAndView("searchTemplate/display");
		result.addObject("searchTemplate", searchTemplate);

		return result;
	}

}
