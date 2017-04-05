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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.ChorbiService;
import domain.Actor;
import domain.Chorbi;
import domain.Sense;
import forms.CreateChorbiForm;

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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
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
		result.addObject("principal", principal);
		result.addObject("requestURI", "chorbi/list.do");

		return result;
	}
	// Creation ---------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		CreateChorbiForm createChorbiForm;

		createChorbiForm = new CreateChorbiForm();
		//createChorbiForm = this.chorbiService.constructProfile(this.chorbiService.create());
		result = this.createEditModelAndView(createChorbiForm);

		return result;
	}

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		CreateChorbiForm createChorbiForm;
		Chorbi chorbi;

		chorbi = this.chorbiService.findByPrincipal();
		createChorbiForm = this.chorbiService.constructProfile(chorbi);
		result = this.createEditModelAndView(createChorbiForm);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final CreateChorbiForm createChorbiForm, final BindingResult binding) {

		ModelAndView result;
		Chorbi chorbi;

		if (binding.hasErrors())
			result = this.createEditModelAndView(createChorbiForm);
		else
			try {
				chorbi = this.chorbiService.reconstructProfile(createChorbiForm, "create");
				this.chorbiService.save(chorbi);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(createChorbiForm, "chorbi.commit.error");

			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final CreateChorbiForm createChorbiForm, final BindingResult binding) {

		ModelAndView result;
		Chorbi chorbi;

		if (binding.hasErrors())
			result = this.createEditModelAndView(createChorbiForm);
		else
			try {
				chorbi = this.chorbiService.reconstructProfile(createChorbiForm, "edit");
				this.chorbiService.save(chorbi);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(createChorbiForm, "chorbi.commit.error");

			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final CreateChorbiForm createChorbiForm) {
		ModelAndView result;

		result = this.createEditModelAndView(createChorbiForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CreateChorbiForm createChorbiForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("chorbi/edit");
		result.addObject("createChorbiForm", createChorbiForm);
		result.addObject("requestURI", "chorbi/create.do");
		result.addObject("message", message);

		return result;
	}
}
