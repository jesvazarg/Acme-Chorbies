
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
import domain.Banner;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BannerTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private BannerService	bannerService;


	// Tests ------------------------------------------------------------------

	@Test
	public void testFindById() {
		super.authenticate("admin");
		Banner banner;

		banner = this.bannerService.findOne(118);
		Assert.notNull(banner);

		System.out.println("Id request: " + banner.getId() + " Picture: " + banner.getPicture());
		System.out.println("----------------------------------------");
		this.unauthenticate();
	}

	@Test
	public void testFindAll() {
		super.authenticate("admin");
		Collection<Banner> banners;

		banners = this.bannerService.findAll();
		Assert.isTrue(banners.size() == 3);

		this.unauthenticate();
	}

	@Test
	public void testEditBanner() {
		super.authenticate("admin");
		Collection<Banner> banners;

		banners = this.bannerService.findAll();
		for (final Banner banner : banners) {
			System.out.println("Id request: " + banner.getId() + " Picture antes: " + banner.getPicture());
			banner.setPicture("http://segurosbaratos.motorgiga.com/uploads/comparador_seguros_de_coche.jpg");
			this.bannerService.save(banner);
			System.out.println("Id request: " + banner.getId() + " Picture después: " + banner.getPicture());
			System.out.println("----------------------------------------");
		}

		Assert.isTrue(banners.size() == 3);
		this.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEditBannerNegative1() {
		super.authenticate("admin");
		Banner banner;

		banner = this.bannerService.findOne(0);
		banner.setPicture("http://segurosbaratos.motorgiga.com/uploads/comparador_seguros_de_coche.jpg");
		this.bannerService.save(banner);

		this.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEditBannerNegative2() {
		super.authenticate("admin");
		final Banner banner = null;

		this.bannerService.save(banner);

		this.unauthenticate();
	}

}
