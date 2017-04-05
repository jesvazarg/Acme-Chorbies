
package controllers.chorbi;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChirpService;
import services.ChorbiService;
import controllers.AbstractController;
import domain.Chirp;
import domain.Chorbi;

@Controller
@RequestMapping("/chirp/chorbi")
public class ChirpChorbiController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private ChorbiService	chorbiService;


	// Constructors -----------------------------------------------------------

	public ChirpChorbiController() {
		super();
	}

	// Display ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Chirp> chirps;
		//Boolean isRecipient = false;
		final Chorbi chorbi = this.chorbiService.findByPrincipal();

		chirps = this.chirpService.findChirpByChorbiId(chorbi.getId());

		//			if (message.getRecipient().equals(Chorbi))
		//				isRecipient = true;

		result = new ModelAndView("chirp/list");
		result.addObject("chirps", chirps);
		//result.addObject("isRecipient", isRecipient);

		return result;
	}
	// Display ----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int chirpId) {
		ModelAndView result;
		Chirp chirp;
		//Boolean isRecipient = false;
		final Chorbi chorbi = this.chorbiService.findByPrincipal();

		chirp = this.chirpService.findOne(chirpId);

		//		if (message.getRecipient().equals(Chorbi))
		//			isRecipient = true;

		result = new ModelAndView("message/display");
		result.addObject("chirp", chirp);
		//result.addObject("isRecipient", isRecipient);

		return result;
	}

	// Create ------------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		final Chirp chirp = this.chirpService.create();

		result = this.createEditModelAndView(chirp);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Chirp chirp, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(chirp);
		else
			try {
				this.chirpService.save(chirp);
				result = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(chirp, "chirp.commit.error");
			}

		return result;
	}

	// Response ----------------------------------------------------------------

	//	@RequestMapping(value = "/createResponse", method = RequestMethod.GET)
	//	public ModelAndView response(@RequestParam final int chirpId) {
	//		ModelAndView result;
	//		final Chorbi chorbi = this.chorbiService.findByPrincipal();
	//		final Chirp chirp = this.chirpService.findOne(chirpId);
	//
	//		if (chirp.) {
	//			final Chirp message = this.messageService.response(messageRequest);
	//			result = this.createEditModelAndViewResponse(message);
	//		} else
	//			result = new ModelAndView("redirect:../../folder/Chorbi/list/outBox.do");
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/createResponse", method = RequestMethod.POST, params = "save")
	//	public ModelAndView saveResponse(@Valid final Chirp Chirp, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndViewResponse(Chirp);
	//		else
	//			try {
	//				this.messageService.save(Chirp);
	//				result = new ModelAndView("redirect:../../folder/Chorbi/list/outBox.do");
	//			} catch (final Throwable oops) {
	//				result = this.createEditModelAndViewResponse(Chirp, "message.commit.error");
	//			}
	//
	//		return result;
	//	}

	//Reply ------------------------------------------------------------------------
	//	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	//	public ModelAndView reply(@RequestParam final int messageId) {
	//		ModelAndView result;
	//		final Chorbi Chorbi = this.ChorbiService.findByPrincipal();
	//		final Chirp messageRequest = this.messageService.findOne(messageId);
	//
	//		if (messageRequest.getFolder().getChorbi().equals(Chorbi)) {
	//			final Chirp message = this.messageService.reply(messageRequest);
	//			result = this.createEditModelAndViewReply(message);
	//		} else
	//			result = new ModelAndView("redirect:../../folder/Chorbi/list/outBox.do");
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/forward", method = RequestMethod.POST, params = "save")
	//	public ModelAndView saveReply(@Valid final Chirp Chirp, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		if (binding.hasErrors())
	//			result = this.createEditModelAndViewReply(Chirp);
	//		else
	//			try {
	//				this.messageService.save(Chirp);
	//				result = new ModelAndView("redirect:../../folder/Chorbi/list/outBox.do");
	//			} catch (final Throwable oops) {
	//				result = this.createEditModelAndViewReply(Chirp, "message.commit.error");
	//			}
	//
	//		return result;
	//	}

	//Ancillary methods---------------------------------------------------------

	//Create --------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Chirp chirp) {
		final ModelAndView result = this.createEditModelAndView(chirp, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Chirp chirp, final String message) {
		ModelAndView result;
		Chorbi chorbi;
		Collection<Chorbi> recipients;

		chorbi = this.chorbiService.findByPrincipal();
		recipients = this.chorbiService.findAll();
		recipients.remove(chorbi);

		result = new ModelAndView("message/create");
		result.addObject("chirp", chirp);
		result.addObject("recipients", recipients);
		result.addObject("message", message);
		return result;
	}

	//	//Response --------------------------------------------------------------------
	//	protected ModelAndView createEditModelAndViewResponse(final Chirp message) {
	//		final ModelAndView result = this.createEditModelAndViewResponse(message, null);
	//		return result;
	//	}
	//
	//	protected ModelAndView createEditModelAndViewResponse(final Chirp Chirp, final String message) {
	//		ModelAndView result;
	//
	//		result = new ModelAndView("message/response");
	//		result.addObject("Chirp", Chirp);
	//		result.addObject("message", message);
	//		return result;
	//	}
	//
	//	//Reply ------------------------------------------------------------------------
	//	protected ModelAndView createEditModelAndViewReply(final Chirp message) {
	//		final ModelAndView result = this.createEditModelAndViewReply(message, null);
	//		return result;
	//	}
	//
	//	protected ModelAndView createEditModelAndViewReply(final Chirp Chirp, final String message) {
	//		ModelAndView result;
	//		Chorbi Chorbi;
	//		Collection<Chorbi> recipients;
	//
	//		Chorbi = this.ChorbiService.findByPrincipal();
	//		recipients = this.ChorbiService.findAll();
	//		recipients.remove(Chorbi);
	//
	//		result = new ModelAndView("message/reply");
	//		result.addObject("Chirp", Chirp);
	//		result.addObject("recipients", recipients);
	//		result.addObject("message", message);
	//		return result;
	//	}

}
