
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Chorbi;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdministratorTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ChorbiService			chorbiService;


	// Tests ------------------------------------------------------------------

	// REQUISITOS FUNCIONALES
	// Ban a chorbi, that is, to disable his or her account.
	// Unban a chorbi, which means that his or her account is re-enabled.

	//En este primer driver vamos a banear a un usuario.
	@Test
	public void driverBanearChorbi() {
		final Object testingData[][] = {
			{
				"admin", 127, null
			}, {
				"admin", 128, null
			}, {
				"", 127, IllegalArgumentException.class
			}, {
				"chorbi4", 127, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.banearUsuario((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void banearUsuario(final String user, final int idChorbi, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(user);

			final Chorbi chorbi = this.chorbiService.findOne(idChorbi);
			this.administratorService.banChorbi(chorbi);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

	//En este test vamos a quitarle el baneo a un chorbi

	@Test
	public void driverQuitarBaneoChorbi() {
		final Object testingData[][] = {
			{
				"admin", 127, null
			}, {
				"admin", 128, null
			}, {
				"", 127, IllegalArgumentException.class
			}, {
				"chorbi4", 127, IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.quitarBaneoUsuario((String) testingData[i][0], (int) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	protected void quitarBaneoUsuario(final String user, final int idChorbi, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(user);

			final Chorbi chorbi = this.chorbiService.findOne(idChorbi);
			this.administratorService.desBanChorbi(chorbi);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);

	}

}
