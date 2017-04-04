
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Chorbi;
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
	public void buscadorDeChorbiesCreandoUnoNuevo() {
		super.authenticate("chorbi1");
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		SearchTemplate searchSave;

		final SearchTemplate search = this.searchTemplateService.create();
		search.setRelationship("Love");
		searchSave = this.searchTemplateService.save(search);
		result = this.searchTemplateService.findBySearchResult(searchSave);
		for (final Chorbi aux : result)
			System.out.println(aux.getName());
		super.authenticate(null);
	}

	@Test
	public void buscadorDeChorbiesUsandoUnoCreado() {
		super.authenticate("chorbi1");
		Collection<Chorbi> result = new ArrayList<Chorbi>();
		final SearchTemplate search = this.chorbiService.findByPrincipal().getSearchTemplate();
		System.out.println("===========Buscador===============");
		System.out.println(search.getRelationship());
		System.out.println(search.getResults());
		System.out.println("===========Buscador===============");

		result = this.searchTemplateService.findBySearchResult(search);
		for (final Chorbi aux : result)
			System.out.println(aux.getName());
		super.authenticate(null);
	}

}
