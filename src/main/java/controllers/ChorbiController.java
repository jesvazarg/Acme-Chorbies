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

import security.Authority;
import services.ActorService;
import services.ChorbiService;
import domain.Actor;
import domain.Chorbi;
import domain.Sense;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController extends AbstractController {

	// Service ---------------------------------------------------------------		
	@Autowired
	private ChorbiService	chorbiService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ChorbiController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView action1() {
		ModelAndView result;
		Collection<Chorbi> chorbies;
		Collection<Sense> sensesSent;
		Actor principal;
		Boolean isAdmin;

		principal = this.actorService.findByPrincipal();
		isAdmin = this.actorService.checkAuthority(principal, Authority.ADMIN);
		if (isAdmin) {
			chorbies = this.chorbiService.findAll();
			sensesSent = null;
		} else {
			chorbies = this.chorbiService.findAllNotBanned();
			sensesSent = this.chorbiService.findOne(principal.getId()).getGiveSenses();
		}

		result = new ModelAndView("chorbi/list");
		result.addObject("chorbies", chorbies);
		result.addObject("sensesSent", sensesSent);
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
