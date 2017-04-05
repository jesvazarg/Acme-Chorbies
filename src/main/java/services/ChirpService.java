
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;
import domain.Chorbi;

@Service
@Transactional
public class ChirpService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ChirpRepository	chirpRepository;

	@Autowired
	private ChorbiService	chorbiService;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Chirp findOne(final int chirpId) {
		Assert.isTrue(chirpId != 0);
		Chirp chirp;

		chirp = this.chirpRepository.findOne(chirpId);

		return chirp;
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> result;

		result = this.chirpRepository.findAll();

		return result;
	}

	public Chirp create() {
		Chirp result;
		Chorbi chorbi;

		Calendar calendar;

		result = new Chirp();
		chorbi = this.chorbiService.findByPrincipal();

		calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, -10);

		result.setAttachments(new ArrayList<String>());
		result.setMoment(calendar.getTime());
		result.setSender(chorbi);
		return result;
	}

	//	public Chirp create(final Chirp chirp) {
	//		Chirp result;
	//
	//		result = new Chirp();
	//		result.setSubject(chirp.getSubject());
	//		result.setText(chirp.getText());
	//		result.setMoment(chirp.getMoment());
	//		result.setAttachments(chirp.getAttachments());
	//		result.setSender(chirp.getSender());
	//		result.setRecipient(chirp.getRecipient());
	//
	//		return result;
	//	}

	public Chirp save(Chirp chirp) {
		Assert.notNull(chirp);
		Assert.isTrue(this.validatorURL(chirp.getAttachments()));

		chirp = this.chirpRepository.save(chirp);

		return chirp;
	}

	// Other business methods -------------------------------------------------

	public Collection<Chirp> findChirpByChorbiId(final int chorbiId) {

		Collection<Chirp> result;

		result = this.chirpRepository.findMessagesByChorbiId(chorbiId);

		return result;

	}

	//Devuelve true si la collection esta vacia o si las URLs contenidas en ellas son URLs validas
	public Boolean validatorURL(final Collection<String> lista) {
		Boolean res = false;
		if (!lista.isEmpty()) {
			for (final String aux : lista)
				if (aux.length() > 11) {
					if ((aux.subSequence(0, 11).equals("http://www.") || (aux.subSequence(0, 12).equals("https://www."))))
						res = true;
					else {
						res = false;
						break;
					}
				} else {
					res = false;
					break;
				}
		} else
			res = true;

		return res;
	}
}
