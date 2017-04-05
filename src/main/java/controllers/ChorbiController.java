/*
 * CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import domain.Chorbi;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController extends AbstractController {

	// Service ---------------------------------------------------------------		
	@Autowired
	private ChorbiService	chorbiService;


	// Constructors -----------------------------------------------------------

	public ChorbiController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Chorbi> chorbies;

		chorbies = this.chorbiService.findAll();

		result = new ModelAndView("chorbi/list");
		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "chorbi/list.do");

		return result;
	}
	// Action-2 ---------------------------------------------------------------		

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("customer/action-2");

		return result;
	}
}
