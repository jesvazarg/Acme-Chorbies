
package services;

import java.util.ArrayList;
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
import domain.Chorbi;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChirpTest extends AbstractTest {

	@Autowired
	private ChorbiService	chorbiService;

	@Autowired
	private ChirpService	chirpService;


	// Tests ------------------------------------------------------------------

	//Un actor registrado como chorbi debe ser capaz de ver sus chirps, tanto recibidos
	//como enviados.

	@Test
	public void testFindOne() {
		super.authenticate("chorbi1");
		Chirp chirp;
		chirp = this.chirpService.findOne(133);
		Assert.notNull(chirp);
		this.unauthenticate();
	}

	@Test
	public void testFindAll() {
		super.authenticate("chorbi1");
		Collection<Chirp> chirps;
		chirps = this.chirpService.findAll();
		//Assert.isTrue(chirps.size() == 14);
		this.unauthenticate();
	}

	@Test
	public void sendChirp() {
		super.authenticate("chorbi1");

		Chirp chirp;
		Chorbi recipient;

		chirp = this.chirpService.create();
		recipient = this.chorbiService.findOne(128);
		final Collection<String> attachments = new ArrayList<String>();

		chirp.setSubject("Example subject");
		chirp.setText("Example text");
		chirp.setAttachments(attachments);
		chirp.setRecipient(recipient);

		Assert.notNull(chirp);

		chirp = this.chirpService.save(chirp);

		final Collection<Chirp> all = this.chirpService.findAll();

		Assert.isTrue(all.contains(chirp));

		super.authenticate(null);
	}

	@Test
	public void replyChirp() {
		super.authenticate("chorbi1");

		Chirp chirp;
		final Chirp aux = this.chirpService.findOne(134);
		chirp = this.chirpService.reply(aux);
		final Collection<String> attachments = new ArrayList<String>();

		chirp.setSubject("Example reply");
		chirp.setText("Example reply");
		chirp.setAttachments(attachments);

		Assert.notNull(chirp);

		chirp = this.chirpService.save(chirp);

		final Collection<Chirp> all = this.chirpService.findAll();

		Assert.isTrue(all.contains(chirp));

		super.authenticate(null);
	}

	@Test
	public void forwardChirp() {
		super.authenticate("chorbi1");

		Chirp chirp;
		final Chorbi recipient;
		final Chirp aux = this.chirpService.findOne(133);
		chirp = this.chirpService.forward(aux);
		recipient = this.chorbiService.findOne(130);
		final Collection<String> attachments = new ArrayList<String>();

		chirp.setSubject("Example forward");
		chirp.setText("Example forward");
		chirp.setAttachments(attachments);
		chirp.setRecipient(recipient);

		Assert.notNull(chirp);

		chirp = this.chirpService.save(chirp);

		final Collection<Chirp> all = this.chirpService.findAll();

		Assert.isTrue(all.contains(chirp));

		super.authenticate(null);
	}

	@Test
	public void deleteChirp() {
		super.authenticate("chorbi1");

		final Chirp chirp = this.chirpService.findOne(133);
		this.chirpService.delete(chirp);

		final Collection<Chirp> all = this.chirpService.findAll();

		Assert.isTrue(!all.contains(chirp));

		super.authenticate(null);
	}

	//Enviar un mensaje (negativo)

	//Con chirp null
	@Test(expected = IllegalArgumentException.class)
	public void testSendChirpNegative1() {
		super.authenticate("chorbi1");

		Chirp chirp;

		chirp = this.chirpService.save(null);
		super.authenticate(null);
	}

	//Sin chorbi
	@Test(expected = IllegalArgumentException.class)
	public void sendChirpNegative2() {
		//super.authenticate("chorbi1");

		Chirp chirp;
		Chorbi recipient;

		chirp = this.chirpService.create();
		recipient = this.chorbiService.findOne(128);
		final Collection<String> attachments = new ArrayList<String>();

		chirp.setSubject("Example subject");
		chirp.setText("Example text");
		chirp.setAttachments(attachments);
		chirp.setRecipient(recipient);

		chirp = this.chirpService.save(chirp);

	}

	//Con admin
	@Test(expected = IllegalArgumentException.class)
	public void sendChirpNegative3() {
		super.authenticate("admin");

		Chirp chirp;
		Chorbi recipient;

		chirp = this.chirpService.create();
		recipient = this.chorbiService.findOne(128);
		final Collection<String> attachments = new ArrayList<String>();

		chirp.setSubject("Example subject");
		chirp.setText("Example text");
		chirp.setAttachments(attachments);
		chirp.setRecipient(recipient);

		chirp = this.chirpService.save(chirp);

		super.authenticate(null);
	}

	//Responder un mensaje (negativo)

	@Test(expected = IllegalArgumentException.class)
	public void replyChirpNegative() {
		super.authenticate("chorbi1");

		Chirp chirp;
		chirp = this.chirpService.reply(null);
		final Collection<String> attachments = new ArrayList<String>();

		chirp.setSubject("Example reply");
		chirp.setText("Example reply");
		chirp.setAttachments(attachments);

		chirp = this.chirpService.save(chirp);

		super.authenticate(null);
	}

	//Reenviar un mensaje

	@Test(expected = IllegalArgumentException.class)
	public void forwardChirpNegative() {
		super.authenticate("chorbi1");

		Chirp chirp;
		final Chorbi recipient;
		chirp = this.chirpService.forward(null);
		recipient = this.chorbiService.findOne(130);
		final Collection<String> attachments = new ArrayList<String>();

		chirp.setSubject("Example forward");
		chirp.setText("Example forward");
		chirp.setAttachments(attachments);
		chirp.setRecipient(recipient);

		chirp = this.chirpService.save(chirp);
		super.authenticate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteChirpNegative() {
		super.authenticate("chorbi3");

		final Chirp chirp = this.chirpService.findOne(133);
		this.chirpService.delete(chirp);

		super.authenticate(null);
	}
}
