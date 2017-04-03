
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;

@Service
@Transactional
public class ChirpService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ChirpRepository	chirpRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------
	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Chirp findOne(final int chirpId) {
		Chirp chirp;

		chirp = this.chirpRepository.findOne(chirpId);
		Assert.notNull(chirp);

		return chirp;
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> result;

		result = this.chirpRepository.findAll();

		return result;
	}

	public Chirp save(Chirp chirp) {
		Assert.notNull(chirp);

		chirp = this.chirpRepository.save(chirp);

		return chirp;
	}

	// Other business methods -------------------------------------------------

}
