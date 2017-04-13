
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Chorbi;
import domain.Coordinate;
import domain.SearchTemplate;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SearchTemplateTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private SearchTemplateService	searchTemplateService;

	@Autowired
	private ChorbiService			chorbiService;


	// Tests ------------------------------------------------------------------
	//Un actor registrado como chorbi debe ser capaz de buscar a otros chorbies mediante
	//un buscador

	@Test
	public void driverBuscadorDeChorbies() {
		final Object testingData[][] = {
			{
				"chorbi3", "", "Woman", 80, "Sevilla", "España", "", "Andalucia", "", null
			}, {
				"chorbi3", "Love", "Man", 35, "", "", "", "Andalucia", "", null
			}, {
				"chorbi3", "Love", "Man", 35, "Sevilla", "España", "", "Andalucia", "rico", null
			}, {
				"chorbi3", "Love", "Man", 35, "Sevilla", "España", "", "", "", null
			}, {
				"chorbi3", "Love", "Man", 35, "Sevilla", "España", "", "Andalucia", "", null
			}, {
				"chorbi3", "Activities", "Man", 40, "Sevilla", "España", "", "Andalucia", "", null
			}, {
				"chorbi3", "Friendship", "Woman", 25, "Sevilla", "España", "", "Andalucia", "", null
			}, {
				"chorbi3", "Love", "Woman", 25, "", "", "", "", "", null
			}, {
				"chorbi3", "", "Woman", 25, "", "", "", "", "playa", null
			}, {
				"chorbi3", "", "", 25, "", "", "", "", "", null
			}, {
				"chorbi3", "", "", 25, "", "", "", "", "guapo", null
			}, {
				"chorbi3", "", "", 25, "Sevilla", "España", "", "Andalucia", "", null
			}, {
				"chorbi3", "", "", 25, "Sevilla", "Francia", "", "", "queso", null
			}, {
				"chorbi3", "Friendship", "Woman", 25, "Sevilla", "España", "", "Andalucia", "", null
			}, {
				"", "Love", "Man", 35, "Sevilla", "España", "", "Andalucia", "", IllegalArgumentException.class
			}, {
				"admin", "Love", "Man", 35, "Sevilla", "España", "", "Andalucia", "", IllegalArgumentException.class
			}, {
				"chorbi6", "Love", "Man", 35, "Sevilla", "España", "", "Andalucia", "", IllegalArgumentException.class
			}, {
				"chorbi1", "Love", "Man", 35, "Sevilla", "España", "", "Andalucia", "", IllegalArgumentException.class
			}, {
				"chorbi3", "Love", "Woman", 250, "", "", "", "", "", ConstraintViolationException.class
			}, {
				"chorbi3", "Love", "Woman", -1, "", "", "", "", "", ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.buscadorDeChorbies((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (int) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (Class<?>) testingData[i][9]);
	}

	protected void buscadorDeChorbies(final String username, final String relationship, final String genre, final int edad, final String ciudad, final String pais, final String estado, final String provincia, final String keyword, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);
			final Chorbi chorbi = this.chorbiService.findByPrincipal();
			SearchTemplate search = chorbi.getSearchTemplate();
			search.setRelationship(relationship);
			search.setGenre(genre);
			search.setAge(edad);
			/* Coordinate */
			final Coordinate cor = search.getCoordinate();
			cor.setCity(ciudad);
			cor.setCountry(pais);
			cor.setProvince(provincia);
			cor.setState(estado);
			search.setCoordinate(cor);

			search = this.searchTemplateService.save(search);
			chorbi.setSearchTemplate(search);
			this.chorbiService.save(chorbi);
			this.searchTemplateService.findBySearchResult(search);
			this.searchTemplateService.findAll();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
}
