
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private ChorbiService	chorbiService;


	// Constructors -----------------------------------------------------------
	public DashboardController() {
		super();
	}

	// Dashboard ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		result = new ModelAndView("administrator/dashboard");
		//Level C -------------------------------------------------
		// C1
		final Collection<Object[]> numberChorbiPerCountryAndCity;
		numberChorbiPerCountryAndCity = this.chorbiService.numberChorbiPerCountryAndCity();
		result.addObject("numberChorbiPerCountryAndCity", numberChorbiPerCountryAndCity);

		// C2
		final Double[] minMaxAvgAgeOfChorbi2 = this.chorbiService.minMaxAvgAgeOfChorbi2();
		result.addObject("minMaxAvgAgeOfChorbi2", minMaxAvgAgeOfChorbi2);

		//C3
		final Double ratioChorbiesWithNullOrInvalidCreditcard = this.chorbiService.ratioChorbiesWithNullOrInvalidCreditcard();
		result.addObject("ratioChorbiesWithNullOrInvalidCreditcard", ratioChorbiesWithNullOrInvalidCreditcard);

		return result;

	}
}
