
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChorbiRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Chirp;
import domain.Chorbi;
import domain.CreditCard;
import domain.Sense;

@Service
@Transactional
public class ChorbiService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ChorbiRepository	chorbiRepository;


	// Supporting services ----------------------------------------------------

	// Constructors------------------------------------------------------------
	public ChorbiService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Chorbi findOne(final int chorbiId) {
		Chorbi result;

		result = this.chorbiRepository.findOne(chorbiId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Chorbi> findAll() {
		Collection<Chorbi> result;

		result = this.chorbiRepository.findAll();

		return result;
	}

	public Chorbi create() {
		Chorbi result;
		UserAccount userAccount;
		Authority authority;
		Collection<Sense> giveSenses;
		Collection<Sense> reciveSenses;
		Collection<Chirp> sentChirps;
		Collection<Chirp> reciveChirps;

		userAccount = new UserAccount();
		authority = new Authority();
		giveSenses = new ArrayList<Sense>();
		reciveSenses = new ArrayList<Sense>();
		sentChirps = new ArrayList<Chirp>();
		reciveChirps = new ArrayList<Chirp>();

		authority.setAuthority(Authority.CHORBI);
		userAccount.addAuthority(authority);

		result = new Chorbi();
		result.setUserAccount(userAccount);
		result.setGiveSenses(giveSenses);
		result.setReciveSenses(reciveSenses);
		result.setSentChirps(sentChirps);
		result.setReciveChirps(reciveChirps);

		return result;
	}

	public Chorbi save(final Chorbi chorbi) {
		Assert.notNull(chorbi);
		final Chorbi result;

		Assert.isTrue(chorbi.getBirthDate().getTime() <= (Calendar.getInstance().getTimeInMillis() - 568024200000L));
		if (chorbi.getCreditCard() != null) {
			final CreditCard card = chorbi.getCreditCard();
			final Calendar expiryDate = Calendar.getInstance();
			expiryDate.set(card.getExpirationYear(), card.getExpirationMonth() - 1, 1);
			final Calendar today = Calendar.getInstance();
			expiryDate.add(Calendar.DAY_OF_YEAR, -1);
			Assert.isTrue(expiryDate.after(today));

			final String brandName = card.getBrandName().toUpperCase();
			Assert.isTrue(brandName == "VISA" || brandName == "MASTERCARD" || brandName == "DISCOVER" || brandName == "DINNERS" || brandName == "AMEX");
		}
		result = this.chorbiRepository.save(chorbi);
		return result;
	}

	// Other business methods -------------------------------------------------
	public Chorbi findByPrincipal() {
		Chorbi result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Chorbi findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Chorbi result;

		result = this.chorbiRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public Integer edadChorbi(final Chorbi chorbi) {
		Assert.notNull(chorbi);
		Integer result;
		Date calendarHoy;
		Date cumple;

		cumple = chorbi.getBirthDate();
		calendarHoy = Calendar.getInstance().getTime();

		result = calendarHoy.getYear() - cumple.getYear();
		return result;
	}
}
