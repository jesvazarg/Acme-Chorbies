
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Chirp;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChirpTest extends AbstractTest {

	@Autowired
	private ChirpService	chirpService;


	// Tests ------------------------------------------------------------------
	//Un actor registrado como chorbi debe ser capaz de ver sus chirps, tanto recibidos
	//como enviados.

	@Test
	public void testFindOne() {
		Chirp chirp;
		chirp = this.chirpService.findOne(124);
		Assert.notNull(chirp);
	}

	@Test
	public void testFinAll() {
		Collection<Chirp> chirps;
		chirps = this.chirpService.findAll();
		Assert.isTrue(chirps.size() == 14);
	}

}
